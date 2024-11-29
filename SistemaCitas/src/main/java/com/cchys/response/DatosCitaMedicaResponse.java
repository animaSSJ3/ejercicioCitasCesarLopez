package com.cchys.response;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DatosCitaMedicaResponse {

	private Long id;
	private LocalDate fechaCita;
	private String horaCita;
	private String estadoCita;
	private Integer estado;
	private DatosPacienteResponse idPaciente;
	private CatalogoTipoCitaResponse idTipoCita;
	private DatosConsultorioResponse idConsultorio;
	
	
}
