package com.cchys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cchys.entities.CatalogoAreaEntity;
import com.cchys.entities.DatosConsultorioEntity;
import java.util.List;
import java.util.Optional;


public interface DatosConsultorioRepository extends JpaRepository<DatosConsultorioEntity, String>{

	List<DatosConsultorioEntity> findByIdArea(CatalogoAreaEntity idArea);
	
	Optional<DatosConsultorioEntity> findByCodigo(String codigo);
	
}
