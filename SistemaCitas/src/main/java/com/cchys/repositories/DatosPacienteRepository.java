package com.cchys.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cchys.entities.DatosPacienteEntity;

public interface DatosPacienteRepository extends JpaRepository<DatosPacienteEntity, String>{

	@Query("SELECT MAX(dpe.credencial) FROM DatosPacienteEntity dpe")
	Optional<String> findMaxCredencial();
	
}
