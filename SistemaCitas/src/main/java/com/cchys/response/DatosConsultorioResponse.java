package com.cchys.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DatosConsultorioResponse {

	private Long id;
	private String codigo;
	private String clave;
	private String nombre;
	private Integer estado;
	private CatalogoTurnoResponse idTurno;
	private CatalogoAreaResponse idArea;
	private CatalogoMedicoResponse idMedico;
	
}
