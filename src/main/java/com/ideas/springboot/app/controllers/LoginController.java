package com.ideas.springboot.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * The Class LoginController.
 * @author Israel Bejarano
 */
@Controller
public class LoginController {
	
	/**
	 * Login.
	 *
	 * @param error the error
	 * @param model the model
	 * @param principal the principal
	 * @param flash the flash
	 * @return the string
	 */
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal, RedirectAttributes flash) {
		if(principal != null) {
			flash.addFlashAttribute("info", "Ya ha iniciado sesión anteriormente");
			return "redirect:/";
		}
		if(error != null) {
			model.addAttribute("error", "Error en el login: nombre de usuario o password incorrecto, por favor vuelva a intentarlo.");
		}
		return "login";
	}
}