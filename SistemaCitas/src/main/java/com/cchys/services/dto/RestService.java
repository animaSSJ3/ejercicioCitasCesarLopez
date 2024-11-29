package com.cchys.services.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cchys.entities.DatosClienteEntity;
import com.cchys.request.AuthRequest;
import com.cchys.response.AuthResponse;
import com.cchys.response.CatalogoAreaResponse;
import com.cchys.response.CatalogoMedicoResponse;
import com.cchys.response.CatalogoTipoCitaResponse;
import com.cchys.response.CatalogoTurnoResponse;
import com.cchys.response.DatosCitaMedicaResponse;
import com.cchys.response.DatosConsultorioResponse;
import com.cchys.response.DatosPacienteResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@RequiredArgsConstructor
@Slf4j
public class RestService {

	private final RestTemplate restTemplate;

	@Value("${base.url.api}")
	private String ruta_api;

	// SE CREA METODOS PARA AGREGAR EN LAS CABECERAS ALGUNOS
	// ELEMENTOS O DATOS DE AUTENTICACION QUE REQUIERE EL API
	private HttpHeaders setHeaders(String token) {

		HttpHeaders headers = new HttpHeaders();

		if (token.isBlank() || token.isEmpty()) {

			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		} else {

			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", "Bearer " + token);

		}

		return headers;
	}

	public AuthResponse obtieneToken(AuthRequest datos) {

		try {

			ResponseEntity<AuthResponse> response = restTemplate.postForEntity(ruta_api + "obtieneToken", datos,
					AuthResponse.class);

			return response.getBody();

		} catch (Exception ex) {
			log.info("Token Error: " + ex.getMessage());
			return null;
		}

	}

	public List<CatalogoAreaResponse> listaProductos(String token) {

		try {

			HttpEntity<String> entity = new HttpEntity<String>(this.setHeaders(token));
			ResponseEntity<List<CatalogoAreaResponse>> response = this.restTemplate.exchange(ruta_api + "obtiene-areas",
					HttpMethod.GET, entity, new ParameterizedTypeReference<List<CatalogoAreaResponse>>() {
					});
			return response.getBody();

		} catch (Exception ex) {
			log.info("Lista Areas Error: " + ex.getMessage());
			return null;
		}

	}
	
	public List<CatalogoMedicoResponse> listaMedicos(String token) {

		try {

			HttpEntity<String> entity = new HttpEntity<>(this.setHeaders(token));
			ResponseEntity<List<CatalogoMedicoResponse>> response = this.restTemplate.exchange(ruta_api + "obtiene-medicos",
					HttpMethod.GET, entity, new ParameterizedTypeReference<List<CatalogoMedicoResponse>>() {
					});
			return response.getBody();

		} catch (Exception ex) {
			log.info("Lista Medicos Error: " + ex.getMessage());
			return null;
		}

	}
	
	public List<CatalogoTipoCitaResponse> listaTipoCita(String token) {

		try {

			HttpEntity<String> entity = new HttpEntity<>(this.setHeaders(token));
			ResponseEntity<List<CatalogoTipoCitaResponse>> response = this.restTemplate.exchange(ruta_api + "obtiene-tipo-cita",
					HttpMethod.GET, entity, new ParameterizedTypeReference<List<CatalogoTipoCitaResponse>>() {
					});
			return response.getBody();

		} catch (Exception ex) {
			log.info("Lista Tipo Cita Error: " + ex.getMessage());
			return null;
		}

	}
	
	public List<CatalogoTurnoResponse> listaTurnos(String token) {

		try {

			HttpEntity<String> entity = new HttpEntity<>(this.setHeaders(token));
			ResponseEntity<List<CatalogoTurnoResponse>> response = this.restTemplate.exchange(ruta_api + "obtiene-turno",
					HttpMethod.GET, entity, new ParameterizedTypeReference<List<CatalogoTurnoResponse>>() {
					});
			return response.getBody();

		} catch (Exception ex) {
			log.info("Lista Turno Error: " + ex.getMessage());
			return null;
		}

	}
	
	public CatalogoAreaResponse areaFiltrada(String token, CatalogoAreaResponse area) {

		try {

			HttpEntity<CatalogoAreaResponse> entity = new HttpEntity<>(area,this.setHeaders(token));
			ResponseEntity<CatalogoAreaResponse> response = this.restTemplate.exchange(ruta_api + "area-filtro",
					HttpMethod.POST, entity, new ParameterizedTypeReference<CatalogoAreaResponse>() {
					});
			return response.getBody();

		} catch (Exception ex) {
			log.info("Lista Areas Error: " + ex.getMessage());
			return null;
		}

	}
	
	public List<DatosConsultorioResponse> consultoriosFiltrados(String token, CatalogoAreaResponse area) {

		try {

			HttpEntity<CatalogoAreaResponse> entity = new HttpEntity<>(area,this.setHeaders(token));
			ResponseEntity<List<DatosConsultorioResponse>> response = this.restTemplate.exchange(ruta_api + "consultorio-filtro",
					HttpMethod.POST, entity, new ParameterizedTypeReference<List<DatosConsultorioResponse>>() {
					});
			return response.getBody();

		} catch (Exception ex) {
			log.info("Lista Consultorio Filtrado Error: " + ex.getMessage());
			return null;
		}

	}
	
	public List<DatosCitaMedicaResponse> citasPaciente(String token, DatosPacienteResponse paciente) {

		try {

			HttpEntity<DatosPacienteResponse> entity = new HttpEntity<>(paciente,this.setHeaders(token));
			ResponseEntity<List<DatosCitaMedicaResponse>> response = this.restTemplate.exchange(ruta_api + "citas-paciente",
					HttpMethod.POST, entity, new ParameterizedTypeReference<List<DatosCitaMedicaResponse>>() {
					});
			return response.getBody();

		} catch (Exception ex) {
			log.info("Lista Cita Medica Paciente Error: " + ex.getMessage());
			return null;
		}

	}
	
	public DatosPacienteResponse obtienePaciente(String token, DatosClienteEntity cliente) {

		try {

			HttpEntity<DatosClienteEntity> entity = new HttpEntity<>(cliente,this.setHeaders(token));
			ResponseEntity<DatosPacienteResponse> response = this.restTemplate.exchange(ruta_api + "paciente",
					HttpMethod.POST, entity, new ParameterizedTypeReference<DatosPacienteResponse>() {
					});
			return response.getBody();

		} catch (Exception ex) {
			log.info("Datos Paciente Error: " + ex.getMessage());
			return null;
		}

	}
	
	public DatosCitaMedicaResponse actualizaCita(String token, DatosCitaMedicaResponse cita) {

		try {

			HttpEntity<DatosCitaMedicaResponse> entity = new HttpEntity<>(cita,this.setHeaders(token));
			ResponseEntity<DatosCitaMedicaResponse> response = this.restTemplate.exchange(ruta_api + "cambios-citas",
					HttpMethod.POST, entity, new ParameterizedTypeReference<DatosCitaMedicaResponse>() {
					});
			return response.getBody();

		} catch (Exception ex) {
			log.info("Actualiza cita Error: " + ex.getMessage());
			return null;
		}

	}
	
	public CatalogoTipoCitaResponse tipoCita(String token, CatalogoTipoCitaResponse tipoCita) {

		try {

			HttpEntity<CatalogoTipoCitaResponse> entity = new HttpEntity<>(tipoCita,this.setHeaders(token));
			ResponseEntity<CatalogoTipoCitaResponse> response = this.restTemplate.exchange(ruta_api + "area-filtro",
					HttpMethod.POST, entity, new ParameterizedTypeReference<CatalogoTipoCitaResponse>() {
					});
			return response.getBody();

		} catch (Exception ex) {
			log.info("Lista Areas Error: " + ex.getMessage());
			return null;
		}

	}
	
	public DatosConsultorioResponse tipoConsultorio(String token, DatosConsultorioResponse consul) {

		try {

			HttpEntity<DatosConsultorioResponse> entity = new HttpEntity<>(consul,this.setHeaders(token));
			ResponseEntity<DatosConsultorioResponse> response = this.restTemplate.exchange(ruta_api + "area-filtro",
					HttpMethod.POST, entity, new ParameterizedTypeReference<DatosConsultorioResponse>() {
					});
			return response.getBody();

		} catch (Exception ex) {
			log.info("Lista Areas Error: " + ex.getMessage());
			return null;
		}

	}
	
	

}
