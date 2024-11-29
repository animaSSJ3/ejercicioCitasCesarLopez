package com.cchys.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "datos_citas")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DatosCitaMedicaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@NotNull(message = "es requerido")
	@Column(name = "fecha_cita")
	private LocalDate fechaCita;
	@NotEmpty(message = "esta vacio")
	@Column(name = "hora_cita", length = 20)
	private String horaCita;
	@NotEmpty(message = "esta vacio")
	@Column(name = "estado_cita", length = 20)
	private String estadoCita;
	@Column(name = "estado")
	private Integer estado;
	@OneToOne
	@JoinColumn(name = "idPaciente")
	private DatosPacienteEntity idPaciente;
	@OneToOne
	@JoinColumn(name = "idTipoCita")
	private CatalogoTipoCitaEntity idTipoCita;
	@OneToOne
	@JoinColumn(name = "idConsultorio")
	private DatosConsultorioEntity idConsultorio;
	
	
}
