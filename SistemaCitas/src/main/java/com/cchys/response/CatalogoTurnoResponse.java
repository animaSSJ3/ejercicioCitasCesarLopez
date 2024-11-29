package com.cchys.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogoTurnoResponse {

	private Long id;
	private String codigo;
	private String nombre;
	private Integer estado;
	
}
