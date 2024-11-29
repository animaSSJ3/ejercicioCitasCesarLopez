package com.cchys.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "datos_cliente")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DatosClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@NotEmpty(message = "esta vacio")
	@Column(name = "nombre", length = 100)
	private String nombre;
	@NotEmpty(message = "esta vacio")
	@Column(name = "primer_apellido", length = 100)
	private String primerApellido;
	@NotEmpty(message = "esta vacio")
	@Column(name = "segundo_apellido", length = 100)
	private String segundoApellido;
	@NotNull(message = "es requerido")
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	@Column(name = "edad")
	private Integer edad;
	@NotEmpty(message = "esta vacio")
	@Column(name = "telefono", length = 10)
	private String telefono;
	@Column(name = "estado")
	private Integer estado;

}
