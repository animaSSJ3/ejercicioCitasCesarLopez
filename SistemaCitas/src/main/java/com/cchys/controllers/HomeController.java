package com.cchys.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cchys.entities.DatosUsuariosEntity;
import com.cchys.entities.dto.login.LoginUserDTO;
import com.cchys.request.AuthRequest;
import com.cchys.request.SolicitudCitaDTO;
import com.cchys.response.CatalogoAreaResponse;
import com.cchys.response.CatalogoMedicoResponse;
import com.cchys.response.CatalogoTipoCitaResponse;
import com.cchys.response.CatalogoTurnoResponse;
import com.cchys.response.DatosCitaMedicaResponse;
import com.cchys.response.DatosConsultorioResponse;
import com.cchys.response.DatosPacienteResponse;
import com.cchys.services.DatosUsuariosService;
import com.cchys.services.dto.RestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard-admin")
@Slf4j
public class HomeController {

	private final DatosUsuariosService usuariosService;
	
	private final RestService restService;

	@Value("${base.url.api}")
	private String ruta_api;

	@GetMapping("/home")
	public String home() {

		return "home/admin/home";

	}
	
	
	@PostMapping("/citaArea")
	public String citaArea(@Validated CatalogoAreaResponse datosArea, BindingResult result,
			RedirectAttributes flash, Principal principal, Model model) {
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			
			System.out.println("Error");
			
			model.addAttribute("errores", errores);
			model.addAttribute("areasObtiene", datosArea);
			return "home/admin/home";

		}
		
		
		
		if (principal != null) {
			
			if (!datosArea.getCodigo().equals("ZZ")) {
				
				
				List<DatosConsultorioResponse> consultorio = this.restService.consultoriosFiltrados(ruta_api, datosArea);
				
				if (!consultorio.isEmpty()) {
					
					
					
					flash.addFlashAttribute("clase", "success");
					flash.addFlashAttribute("mensaje", "Area Seleccionada");
					flash.addFlashAttribute("consultorio", consultorio);
					return "redirect:/dashboard-admin/registro";
					
				}else {
					
					flash.addFlashAttribute("clase", "danger");
					flash.addFlashAttribute("mensaje", "Datos no encontrados");
					return  "redirect:/dashboard-admin/home";
					
				}
				
				
			}else {
				
				flash.addFlashAttribute("clase", "danger");
				flash.addFlashAttribute("mensaje", "Selecciona una area valida");
				return  "redirect:/dashboard-admin/home";
				
			}
			
			
		}else {
			
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("mensaje", "Debes acceder con tu usuario");
			return "redirect:/acceso/login";
			
		}
		
		
	}
	
	@GetMapping("/registro")
	public String registro(Model model) {

		SolicitudCitaDTO datosCitaRegistra = new SolicitudCitaDTO();
		model.addAttribute("datosCitaRegistra",datosCitaRegistra);
		return "home/admin/panel/registro";

	}
	
	@PostMapping("/registroCita")
	public String registroCita(@Validated SolicitudCitaDTO datosCitaRegistra, BindingResult result,
			RedirectAttributes flash, Principal principal, Model model) {
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			
			System.out.println("Error");
			
			model.addAttribute("errores", errores);
			model.addAttribute("datosCitaRegistra", datosCitaRegistra);
			return "home/admin/panel/registro";

		}
		
		
		
		if (principal != null) {
			
			DatosUsuariosEntity usuario = this.usuariosService.buscarPorCorreo(principal.getName(), 1);
			DatosPacienteResponse paciente = this.restService.obtienePaciente(usuario.getToken(), usuario.getIdCliente());
			
			CatalogoTipoCitaResponse tipoCita1 = new CatalogoTipoCitaResponse();
			tipoCita1.setCodigo(datosCitaRegistra.getIdConsultorio());
			
			CatalogoTipoCitaResponse tipoCita = this.restService.tipoCita(usuario.getToken(), tipoCita1);
			
			DatosConsultorioResponse consul2 = new DatosConsultorioResponse();
			consul2.setCodigo(datosCitaRegistra.getIdConsultorio());
			
			DatosConsultorioResponse consul = this.restService.tipoConsultorio(usuario.getToken(), consul2);
			
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedDate = datosCitaRegistra.getFechaCita().format(dateTimeFormatter); // 17-02-2022
			LocalDate fechaCita = LocalDate.parse(formattedDate, dateTimeFormatter);
			
			DatosCitaMedicaResponse cita = DatosCitaMedicaResponse.builder()
					
					.fechaCita(fechaCita)
					.horaCita(datosCitaRegistra.getHoraCita())
					.estadoCita("CREADA")
					.estado(1)
					.idPaciente(paciente)
					.idTipoCita(tipoCita)
					.idConsultorio(consul)
					.build();
			this.restService.actualizaCita(usuario.getToken(), cita);

			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("mensaje", "Registro completado");
			return "redirect:/dashboard-admin/home";

			
			
		}else {
			
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("mensaje", "Debes acceder con tu usuario");
			return "redirect:/acceso/login";
			
		}
		
		
	}
	
	@GetMapping("/datosCita")
	public String datosCita(Model model) {

		return "home/admin/panel/tabla";

	}
	
	@PostMapping("/eliminar")
	public String eliminarContactos(@RequestParam(name = "id") Long id, RedirectAttributes flash, Principal principal) {

		try {

			System.out.println("ID Recibido: " + id);
			
			DatosUsuariosEntity usuario = this.usuariosService.buscarPorCorreo(principal.getName(), 1);
			DatosCitaMedicaResponse citaBusca = this.restService.buscaCita(usuario.getToken(),id);
			
			citaBusca.setEstadoCita("CANCELADA");
			
			System.out.println("Contacto Actualizado: " + citaBusca);
			
			this.restService.actualizaCita(usuario.getToken(),citaBusca);

			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("mensaje", "Se elimino la cita medica");
			return "redirect:/dashboard-admin/home";

		} catch (Exception e) {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("mensaje", "Error al eliminar la cita: " + e.getMessage());
			return "redirect:/dashboard-admin/home";
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
			
			AuthRequest datosToken = new AuthRequest();
			
			DatosPacienteResponse pacienteBusca = this.restService.obtienePaciente(usuario.getToken(), usuario.getIdCliente());
			
			List<CatalogoAreaResponse> listaAreas = this.restService.listaProductos(usuario.getToken());
			List<CatalogoMedicoResponse> listaMedicos = this.restService.listaMedicos(usuario.getToken());
			List<CatalogoTipoCitaResponse> listaTipoCita = this.restService.listaTipoCita(usuario.getToken());
			List<CatalogoTurnoResponse> listaTurnos = this.restService.listaTurnos(usuario.getToken());
			List<DatosCitaMedicaResponse> listaCitas = this.restService.citasPaciente(usuario.getToken(), pacienteBusca);

			CatalogoAreaResponse areasObtiene = new CatalogoAreaResponse();

			System.out.println(pacienteBusca);
			
			model.addAttribute("datos", datos);
			model.addAttribute("datosToken", datosToken);
			model.addAttribute("areasObtiene", areasObtiene);
			model.addAttribute("listaAreas", listaAreas);
			model.addAttribute("listaMedicos", listaMedicos);
			model.addAttribute("listaTipoCita", listaTipoCita);
			model.addAttribute("listaTurnos", listaTurnos);
			model.addAttribute("listaCitas", listaCitas);
			
		}

	}

}
