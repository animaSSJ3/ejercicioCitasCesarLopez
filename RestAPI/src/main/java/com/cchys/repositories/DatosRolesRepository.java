package com.cchys.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cchys.entities.DatosRolesEntity;

public interface DatosRolesRepository extends JpaRepository<DatosRolesEntity, Long>{

	Optional<DatosRolesEntity> findByNombre(String nombre);
	
}
