package com.cchys.services;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cchys.entities.CatalogoTipoCitaEntity;
import com.cchys.repositories.CatalogoTipoCitaRepository;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class CatalogoTipoCitaService {
	
	private final CatalogoTipoCitaRepository repository;
	
	public List<CatalogoTipoCitaEntity> listTipoCita(){
		
		return this.repository.findAll();
		
	}
	
	public CatalogoTipoCitaEntity obtieneTipoCita(String codigo) {
		
		Optional<CatalogoTipoCitaEntity> tipoCita =  this.repository.findByCodigo(codigo);
		
		if(tipoCita.isPresent()) {
			
			return tipoCita.get();
			
		}else {
			return null;
		}
		
	}


}
