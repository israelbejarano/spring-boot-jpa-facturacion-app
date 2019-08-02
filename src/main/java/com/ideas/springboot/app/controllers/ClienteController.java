package com.ideas.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ideas.springboot.app.models.entity.Cliente;
import com.ideas.springboot.app.models.service.IClienteService;

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
	 * Listar.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "listado de clientes");
		model.addAttribute("clientes", clienteService.findAll());
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
	 * @return the string
	 */
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Cliente cliente = null;
		if(id > 0) {
			cliente = clienteService.findOne(id);
		} else {
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
	 * @return the string
	 */
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de cliente");
			return "form";
		}
		clienteService.save(cliente);
		status.setComplete();
		return "redirect:listar";
	}
	
	/**
	 * Eliminar.
	 *
	 * @param id the id
	 * @return the string
	 */
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id > 0) {
			clienteService.delete(id);
		}
		return "redirect:/listar";
	}
}