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
@Table(name = "datos_consultorio")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DatosConsultorioEntity {

	@Column(name = "id")
	private Long id;
	@Id
	@NotEmpty(message = "esta vacio")
	@Column(name = "codigo", length = 3)
	private String codigo;
	@NotEmpty(message = "esta vacio")
	@Column(name = "clave", length = 10)
	private String clave;
	@NotEmpty(message = "esta vacio")
	@Column(name = "nombre", length = 45)
	private String nombre;
	@Column(name = "estado")
	private Integer estado;
	@OneToOne
	@JoinColumn(name = "idTurno")
	private CatalogoTurnoEntity idTurno;
	@OneToOne
	@JoinColumn(name = "idArea")
	private CatalogoAreaEntity idArea;
	@OneToOne
	@JoinColumn(name = "idMedico")
	private CatalogoMedicoEntity idMedico;
	
}
