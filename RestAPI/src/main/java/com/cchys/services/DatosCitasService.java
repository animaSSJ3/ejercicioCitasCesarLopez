package com.cchys.services;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cchys.entities.DatosCitaMedicaEntity;
import com.cchys.entities.DatosPacienteEntity;
import com.cchys.repositories.DatosCitasRepository;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class DatosCitasService {
	
	private final DatosCitasRepository repository;
	
	public List<DatosCitaMedicaEntity> listaCitas(){
		
		return this.repository.findAll();
		
	}
	
	public List<DatosCitaMedicaEntity> listaCitas(DatosPacienteEntity paciente){
		
		return this.repository.findByIdPaciente(paciente);
		
	}
	
	public DatosCitaMedicaEntity guardar(DatosCitaMedicaEntity entity) {
		System.out.println("Error: " + entity);
		return this.repository.save(entity);
	}


}
