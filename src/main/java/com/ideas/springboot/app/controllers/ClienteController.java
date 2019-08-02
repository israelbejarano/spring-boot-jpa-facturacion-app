package com.ideas.springboot.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ideas.springboot.app.models.entity.Cliente;
import com.ideas.springboot.app.models.service.IClienteService;
import com.ideas.springboot.app.util.paginator.PageRender;

/**
 * The Class ClienteController.
 * @author Israel Bejarano
 */
@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	
	/** The cliente service. */
	@Autowired
	private IClienteService clienteService;
	
	/**
	 * Ver.
	 *
	 * @param id the id
	 * @param model the model
	 * @param flash the flash
	 * @return the string
	 */
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = clienteService.findOne(id);
		if(cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Detalle cliente: " + cliente.getNombre());
		
		return "ver";
	}
	
	/**
	 * Listar.
	 *
	 * @param page the page
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 5); // elementos por pagina.
		
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		
		model.addAttribute("titulo", "listado de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}
	
	/**
	 * Crear.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de cliente");
		return "form";
	}
	
	/**
	 * Editar.
	 *
	 * @param id the id
	 * @param model the model
	 * @param flash the flash
	 * @return the string
	 */
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;
		if(id > 0) {
			cliente = clienteService.findOne(id);
			if(cliente == null) {
				flash.addFlashAttribute("error", "El id del cliente no existe");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El id del cliente no puede ser cero");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "form";
	}
	
	/**
	 * Guardar.
	 *
	 * @param cliente the cliente
	 * @param result the result
	 * @param model the model
	 * @param foto the foto
	 * @param flash the flash
	 * @param status the status
	 * @return the string
	 */
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash,SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de cliente");
			return "form";
		}
		if(!foto.isEmpty()) {
			Path directorioRecursos = Paths.get("src//main//resources//static/uploads");
			String rootPath = directorioRecursos.toFile().getAbsolutePath();
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				flash.addFlashAttribute("info", "Has subido correctamente '" + foto.getOriginalFilename() + "'");
				cliente.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String mensajeFlash = (cliente.getId() != null)? "Cliente editado con éxito" : "Cliente creado con éxito";
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 * @param flash the flash
	 * @return the string
	 */
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if(id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito");
		}
		return "redirect:/listar";
	}
}