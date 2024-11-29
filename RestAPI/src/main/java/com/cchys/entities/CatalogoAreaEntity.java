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
@Table(name = "catalogo_area")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CatalogoAreaEntity {
	
	@Column(name = "id")
	private Long id;
	@Id
	@NotEmpty(message = "esta vacio")
	@Column(name = "codigo", length = 3)
	private String codigo;
	@NotEmpty(message = "esta vacio")
	@Column(name = "nombre", length = 100)
	private String nombre;
	@Column(name = "estado")
	private Integer estado;
	

}
