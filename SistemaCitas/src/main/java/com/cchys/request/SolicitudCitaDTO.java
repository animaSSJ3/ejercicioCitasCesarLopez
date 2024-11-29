package com.cchys.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SolicitudCitaDTO {
	
	@NotNull(message = "Es requerido")
	private LocalDate fechaCita;
	@NotEmpty(message = "Esta vacio")
	private String horaCita;
	@NotEmpty(message = "Esta vacio")
	private String idTipoCita;
	@NotEmpty(message = "Esta vacio")
	private String idConsultorio;

}
