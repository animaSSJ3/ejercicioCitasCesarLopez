package com.cchys.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cchys.entities.DatosClienteEntity;
public interface DatosClienteRepository extends JpaRepository<DatosClienteEntity, Long>{
	 
	 Optional<DatosClienteEntity> findById(Long id);

}
