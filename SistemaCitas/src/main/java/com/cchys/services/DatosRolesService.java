package com.cchys.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cchys.entities.DatosRolesEntity;
import com.cchys.repositories.DatosRolesRepository;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class DatosRolesService {

	private final DatosRolesRepository repositorio;

	public void guardar(DatosRolesEntity rol) {
		this.repositorio.save(rol);
	}

}
