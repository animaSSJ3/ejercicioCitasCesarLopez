package com.cchys.services;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cchys.entities.DatosClienteEntity;
import com.cchys.entities.DatosPacienteEntity;
import com.cchys.repositories.DatosPacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class DatosPacienteService {

	
	private final DatosPacienteRepository repositorio;
	
	public DatosPacienteEntity guardar(DatosPacienteEntity datosEntity) {

		return this.repositorio.save(datosEntity);

	}
	
	public String buscaCredencial() {
		
		Optional<String> encuentraCredencial = this.repositorio.findMaxCredencial();
		
		if (encuentraCredencial.isPresent()) {
			
			return encuentraCredencial.get();
			
		}else {
			return null;
		}
		
	}
	
	public DatosPacienteEntity buscaPaciente(DatosClienteEntity cliente) {
		
		Optional<DatosPacienteEntity> encuentraPaciente = this.repositorio.findByIdCliente(cliente);
		
		if (encuentraPaciente.isPresent()) {
			
			return encuentraPaciente.get();
			
		}else {
			return null;
		}
		
	}
	
	
}
