package com.cchys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cchys.entities.DatosCitaMedicaEntity;
import com.cchys.entities.DatosPacienteEntity;

import java.util.List;


public interface DatosCitasRepository extends JpaRepository<DatosCitaMedicaEntity, Long>{

	List<DatosCitaMedicaEntity> findByIdPaciente(DatosPacienteEntity idPaciente);
	
}