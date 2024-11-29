package com.cchys.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.cchys.entities.DatosClienteEntity;
import com.cchys.entities.DatosPacienteEntity;
import com.cchys.entities.DatosRolesEntity;
import com.cchys.entities.DatosUsuariosEntity;
import com.cchys.entities.dto.login.RegistroUserDTO;
import com.cchys.services.DatosClienteService;
import com.cchys.services.DatosPacienteService;
import com.cchys.services.DatosRolesService;
import com.cchys.services.DatosUsuariosService;
import com.cchys.utilities.Utilidades;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/acceso")
public class AccesoController {

	private final DatosUsuariosService usuariosService;

	private final DatosRolesService rolService;
	
	private final DatosClienteService clienteService;
	
	private final DatosPacienteService pacienteService;

	private final PasswordEncoder pasEncode;

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, RedirectAttributes flash,
			Principal principal) {
		if (principal != null) {
			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("mensaje", "Ya ha iniciado sesión anteriormente ");
			return "redirect:/home/admin/home";
		}
		if (error != null) {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("mensaje", "Los datos ingresados no son correctos, por favor vuelva a intentarlo.");
			return "redirect:/acceso/login";
		}
		if (logout != null) {
			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("mensaje", "Ha cerrado sesión exitosamente");
			return "redirect:/acceso/login";
		}
		return "acceso/login";
	}

	@GetMapping("/registro")
	public String registro(Model model) {
		model.addAttribute("cliente", new RegistroUserDTO());
		return "acceso/registro";
	}

	@PostMapping("/registro")
	public String registro_post(@Validated RegistroUserDTO cliente, BindingResult result, RedirectAttributes flash,
			Model model) {
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});

			model.addAttribute("errores", errores);
			model.addAttribute("cliente", cliente);
			return "acceso/registro";
		}

		if (cliente.getPassword().equals(cliente.getConfirmePassword())) {

			if (this.usuariosService.buscarPorCorreo(cliente.getCorreo().trim().toLowerCase(), 1) == null) {
				
				
				Utilidades util = new Utilidades();

				String folio = this.pacienteService.buscaCredencial();
				String folioConsecutivo = "";
				if (folio != null) {
					folioConsecutivo = util.genFolio(folio);
				} else {
					folioConsecutivo = util.genFolio("");
				}
		

				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String formattedDate = cliente.getFechaNacimiento().format(dateTimeFormatter); // 17-02-2022
				LocalDate fechaNac = LocalDate.parse(formattedDate, dateTimeFormatter);

				// CREAMOS EL USUARIO

				DatosClienteEntity datosclienteGuardar = DatosClienteEntity.builder()
						.nombre(cliente.getNombre()).primerApellido(cliente.getPrimerApellido())
						.segundoApellido(cliente.getSegundoApellido()).fechaNacimiento(fechaNac)
						.edad(cliente.getEdad()).telefono(cliente.getTelefono())
						.estado(1).build();

				this.clienteService.guardar(datosclienteGuardar);

				DatosUsuariosEntity usuarioGuardar = DatosUsuariosEntity.builder().correo(cliente.getCorreo())
						.password(pasEncode.encode(cliente.getPassword())).estado(1)
						.idCliente(datosclienteGuardar).build();

				this.usuariosService.guardar(usuarioGuardar);

				DatosRolesEntity rolGuardar = DatosRolesEntity.builder().nombre("ROLE_USER")
						.descripcion("Usuario Predeterminado").estado(1).idUsuario(usuarioGuardar).build();

				this.rolService.guardar(rolGuardar);
				
				DatosPacienteEntity paciente = DatosPacienteEntity.builder()
						.credencial(folioConsecutivo)
						.idCliente(datosclienteGuardar)
						.build();

				this.pacienteService.guardar(paciente);
				
				// REDIRECCIONAMOS
				flash.addFlashAttribute("clase", "success");
				flash.addFlashAttribute("mensaje", "Te has registrado exitosamente");
				return "redirect:/acceso/login";

			} else {

				// REDIRECCIONAMOS
				flash.addFlashAttribute("clase", "danger");
				flash.addFlashAttribute("mensaje", "Correo ya existe");
				return "redirect:/acceso/registro";

			}

		} else {

			// REDIRECCIONAMOS
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("mensaje", "Las contraseñas no son identicas");
			return "redirect:/acceso/registro";

		}

	}


}
