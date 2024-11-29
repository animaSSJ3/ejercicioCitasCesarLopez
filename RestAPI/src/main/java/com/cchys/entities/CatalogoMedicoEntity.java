package com.cchys.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "catalogo_medicos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CatalogoMedicoEntity {
	
	@Column(name = "id")
	private Long id;
	@Id
	@NotEmpty(message = "esta vacio")
	@Column(name = "matricula", length = 50)
	private String matricula;
	@NotEmpty(message = "esta vacio")
	@Column(name = "nombre", length = 150)
	private String nombre;
	@NotEmpty(message = "esta vacio")
	@Column(name = "primer_apellido", length = 100)
	private String primerApellido;
	@NotEmpty(message = "esta vacio")
	@Column(name = "segundo_apellido", length = 100)
	private String segundoApellido;
	@Column(name = "estado")
	private Integer estado;

}
