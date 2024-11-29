package com.cchys.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DatosPacienteResponse {

	private Long id;
	private String credencial;
	private DatosClienteResponse idCliente;
}
