package com.cchys.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "datos_paciente")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DatosPacienteEntity {

	
	@Column(name = "id")
	private Long id;
	@Id
	@NotEmpty(message = "esta vacio")
	@Column(name = "credencial", length = 150)
	private String credencial;
	@OneToOne
	@JoinColumn(name = "idCliente")
	private DatosClienteEntity idCliente;
}
