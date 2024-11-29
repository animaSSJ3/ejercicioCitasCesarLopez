package com.cchys.services;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cchys.entities.CatalogoMedicoEntity;
import com.cchys.repositories.CatalogoMedicoRepository;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class CatalogoMedicosService {
	
	private final CatalogoMedicoRepository repository;
	
	public List<CatalogoMedicoEntity> listMedicos(){
		
		return this.repository.findAll();
		
	}

}
