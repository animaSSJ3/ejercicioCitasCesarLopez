package com.cchys.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cchys.entities.DatosUsuariosEntity;
import com.cchys.entities.dto.login.LoginUserDTO;
import com.cchys.services.DatosUsuariosService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard-user")
public class UsuarioController {
	
	private final DatosUsuariosService usuariosService;
	
	@GetMapping("/home")
	public String home(Principal principal, Model model) {
		
		if (principal != null) {
			setGenericos(principal, model);
			return "home/user/home";
		}else {
			return "";
		}
		
	}
	
	@ModelAttribute
	public void setGenericos(Principal principal, Model model) {
		
		
		if (principal != null) {
			
			LoginUserDTO datos = new LoginUserDTO();
			DatosUsuariosEntity usuario = this.usuariosService.buscarPorCorreo(principal.getName(), 1);
			
			datos.setNombre(usuario.getIdCliente().getNombre());
			datos.setPrimerApellido(usuario.getIdCliente().getPrimerApellido());
			datos.setSegundoApellido(usuario.getIdCliente().getSegundoApellido());
			
			model.addAttribute("datos",datos);
			
		}
		
		
	}

}
