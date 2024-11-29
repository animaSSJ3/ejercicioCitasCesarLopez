package com.cchys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cchys.entities.CatalogoAreaEntity;
import java.util.Optional;


public interface CatalogoAreaRepository extends JpaRepository<CatalogoAreaEntity, String>{

	Optional<CatalogoAreaEntity> findByCodigo(String codigo);
	
}
