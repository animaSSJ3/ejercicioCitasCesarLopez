package com.cchys.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cchys.entities.CatalogoAreaEntity;
import com.cchys.entities.CatalogoMedicoEntity;
import com.cchys.entities.CatalogoTipoCitaEntity;
import com.cchys.entities.CatalogoTurnoEntity;
import com.cchys.entities.DatosCitaMedicaEntity;
import com.cchys.entities.DatosClienteEntity;
import com.cchys.entities.DatosConsultorioEntity;
import com.cchys.entities.DatosPacienteEntity;
import com.cchys.entities.DatosUsuariosEntity;
import com.cchys.jwt.AuthRequest;
import com.cchys.jwt.AuthResponse;
import com.cchys.jwt.JwtTokenUtil;
import com.cchys.services.CatalogoAreaService;
import com.cchys.services.CatalogoMedicosService;
import com.cchys.services.CatalogoTipoCitaService;
import com.cchys.services.CatalogoTurnoService;
import com.cchys.services.DatosCitasService;
import com.cchys.services.DatosConsultorioService;
import com.cchys.services.DatosPacienteService;
import com.cchys.services.DatosUsuariosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AccesoController {

	private final AuthenticationManager authManager;

	private final JwtTokenUtil jwtUtil;

	private final DatosUsuariosService usuarioService;
	
	private final DatosConsultorioService consultorioService;
	
	private final DatosCitasService citasService;
	
	private final DatosPacienteService pacienteService;
	
	private final CatalogoAreaService areaService;
	
	private final CatalogoMedicosService medicosService;
	
	private final CatalogoTipoCitaService tipoCitaService;
	
	private final CatalogoTurnoService turnoService;
	
	
	
	

	/* {"correo":"info@tamila.cl", "password":"123456"} */
	@PostMapping("/obtieneToken")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		try {
			//HACE LA IMPLEMENTACION DE LA AUTENTICACION DEL USUARIO
			Authentication authentication = this.authManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							request.getCorreo(), 
							request.getPassword()));
			System.out.println(authentication);

			DatosUsuariosEntity user = this.usuarioService.buscarPorCorreo(request.getCorreo(),1);
			String accessToken = this.jwtUtil.generarToken(user);

			AuthResponse response = new AuthResponse(request.getCorreo(), accessToken);

			return ResponseEntity.ok(response);

		} catch (BadCredentialsException e) {

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	
	@GetMapping("/obtiene-areas")
	public List<CatalogoAreaEntity> obtieneAreas() {

		return this.areaService.listaAreas();

	}
	
	@GetMapping("/obtiene-medicos")
	public List<CatalogoMedicoEntity> obtieneMedicos() {

		return this.medicosService.listMedicos();

	}
	
	@GetMapping("/obtiene-tipo-cita")
	public List<CatalogoTipoCitaEntity> obtieneTipoCita() {

		return this.tipoCitaService.listTipoCita();

	}
	
	@GetMapping("/obtiene-turno")
	public List<CatalogoTurnoEntity> obtieneTurno() {

		return this.turnoService.listTurno();

	}
	
	@PostMapping("/area-filtro")
	public ResponseEntity<?> obtieneAreaFiltrada(@RequestBody CatalogoAreaEntity area) {

		try {
			
			return ResponseEntity.ok(this.areaService.obtieneFiltroArea(area.getCodigo()));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}

	}
	
	@PostMapping("/consultorio-filtro")
	public ResponseEntity<?> consultorioFiltrado(@RequestBody CatalogoAreaEntity area) {
		
		
		try {
			
			return ResponseEntity.ok(this.consultorioService.listaFiltroConsultorio(area));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}

	}
	
	@PostMapping("/citas-paciente")
	public ResponseEntity<?> citasPaciente(@RequestBody DatosPacienteEntity paciente) {
		
		
		try {
			
			return ResponseEntity.ok(this.citasService.listaCitas(paciente));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}

	}
	
	@PostMapping("/paciente")
	public ResponseEntity<?> citasPaciente(@RequestBody DatosClienteEntity cliente) {
		
		
		try {
			
			return ResponseEntity.ok(this.pacienteService.buscaPaciente(cliente));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}

	}
	
	@PostMapping("/cambios-citas")
	public ResponseEntity<?> cambiosPaciente(@RequestBody DatosCitaMedicaEntity citaMedica) {
		
		
		try {
			
			return ResponseEntity.ok(this.citasService.guardar(citaMedica));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}

	}
	
	@PostMapping("/tipo-cita")
	public ResponseEntity<?> citaTipoBusca(@RequestBody CatalogoTipoCitaEntity tipoCita) {

		try {
			
			return ResponseEntity.ok(this.tipoCitaService.obtieneTipoCita(tipoCita.getCodigo()));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}

	}
	
	@PostMapping("/tipo-consultorio")
	public ResponseEntity<?> consultorio(@RequestBody DatosConsultorioEntity cons) {

		try {
			
			return ResponseEntity.ok(this.consultorioService.obtieneConsultorio(cons.getCodigo()));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}

	}
	
	
	

}
