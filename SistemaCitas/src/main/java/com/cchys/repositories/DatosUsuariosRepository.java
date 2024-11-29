package com.cchys.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cchys.entities.DatosUsuariosEntity;

public interface DatosUsuariosRepository extends JpaRepository<DatosUsuariosEntity, Long>{
	
	Optional<DatosUsuariosEntity> findByCorreoAndEstado(String correo, Integer estado);

}
