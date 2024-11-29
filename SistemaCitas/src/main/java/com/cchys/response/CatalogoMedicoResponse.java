package com.cchys.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogoMedicoResponse {
	
	private Long id;
	private String matricula;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private Integer estado;

}
