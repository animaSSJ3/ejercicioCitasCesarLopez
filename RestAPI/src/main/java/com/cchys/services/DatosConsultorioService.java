package com.cchys.services;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.cchys.entities.CatalogoAreaEntity;
import com.cchys.entities.DatosConsultorioEntity;
import com.cchys.repositories.DatosConsultorioRepository;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class DatosConsultorioService {
	
	private final DatosConsultorioRepository repository;
	
	public List<DatosConsultorioEntity> listaConsultorios(){
		
		return this.repository.findAll();
		
	}
	
	public List<DatosConsultorioEntity> listaFiltroConsultorio(CatalogoAreaEntity area){
		
		return this.repository.findByIdArea(area);
		
	}
	
	public DatosConsultorioEntity obtieneConsultorio(String codigo) {
		
		Optional<DatosConsultorioEntity> consultorioRecuperado =  this.repository.findByCodigo(codigo);
		
		if(consultorioRecuperado.isPresent()) {
			
			return consultorioRecuperado.get();
			
		}else {
			return null;
		}
		
	}
	

}
