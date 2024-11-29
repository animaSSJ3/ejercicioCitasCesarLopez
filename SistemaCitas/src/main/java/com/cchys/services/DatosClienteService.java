package com.cchys.services;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cchys.entities.DatosClienteEntity;
import com.cchys.repositories.DatosClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class DatosClienteService {

	private final DatosClienteRepository repository;

	public DatosClienteEntity guardar(DatosClienteEntity datosEntity) {

		return this.repository.save(datosEntity);

	}


	public DatosClienteEntity buscaPorId(Long id) {

		Optional<DatosClienteEntity> proveedor = this.repository.findById(id);

		if (proveedor.isPresent()) {

			return proveedor.get();

		} else {
			return null;
		}

	}

}
