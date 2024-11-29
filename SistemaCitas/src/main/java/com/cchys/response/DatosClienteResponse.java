package com.cchys.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DatosClienteResponse {

	private Long id;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private LocalDate fechaNacimiento;
	private Integer edad;
	private String telefono;
	private Integer estado;

}
