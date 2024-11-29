package com.cchys.services;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cchys.entities.CatalogoAreaEntity;
import com.cchys.repositories.CatalogoAreaRepository;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class CatalogoAreaService {
	
	private final CatalogoAreaRepository repository;
	
	
	public List<CatalogoAreaEntity> listaAreas(){
		
		return this.repository.findAll();
		
	}
	
	public CatalogoAreaEntity obtieneFiltroArea(String codigo) {
		
		Optional<CatalogoAreaEntity> areaRecuperada =  this.repository.findByCodigo(codigo);
		
		if(areaRecuperada.isPresent()) {
			
			return areaRecuperada.get();
			
		}else {
			return null;
		}
		
	}
	

}
