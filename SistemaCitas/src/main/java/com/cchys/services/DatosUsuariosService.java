package com.cchys.services;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cchys.entities.DatosUsuariosEntity;
import com.cchys.repositories.DatosUsuariosRepository;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class DatosUsuariosService {

	private final DatosUsuariosRepository repositorio;
	
	//PERMITE RETORNAR EL OBJETO QUE FUE INSERTADO EN BASE DE DATOS
	public DatosUsuariosEntity guardar(DatosUsuariosEntity entity) {
		return this.repositorio.save(entity);
	}

	public DatosUsuariosEntity buscarPorCorreo(String correo, Integer estado) {
		
		Optional<DatosUsuariosEntity> usuarioBD = this.repositorio.findByCorreoAndEstado(correo, estado);
		
		if (usuarioBD.isPresent()) {
			return usuarioBD.get();
		}else {
			return null;
		}
		
		
	}

}
