package com.cchys.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cchys.entities.CatalogoTipoCitaEntity;

public interface CatalogoTipoCitaRepository extends JpaRepository<CatalogoTipoCitaEntity, String>{

	Optional<CatalogoTipoCitaEntity> findByCodigo(String codigo);
	
}
