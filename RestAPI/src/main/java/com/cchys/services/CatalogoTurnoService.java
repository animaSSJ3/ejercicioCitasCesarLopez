package com.cchys.services;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cchys.entities.CatalogoTurnoEntity;
import com.cchys.repositories.CatalogoTurnoRepository;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class CatalogoTurnoService {
	
	private final CatalogoTurnoRepository repository;
	
	public List<CatalogoTurnoEntity> listTurno(){
		
		return this.repository.findAll();
		
	}

}
