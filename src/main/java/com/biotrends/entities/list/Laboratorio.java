package com.biotrends.entities.list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.biotrends.entities.BiotrendsBaseEntity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Oscar Malagon
 * @since 17/04/2017
 */
@Data
@Entity
@Table(name = "BIO_LABORATORIO")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Laboratorio extends BiotrendsBaseEntity {

	private static final long serialVersionUID = 1635916755940665924L;

	@Column(name = "LAB_DESC")
	@NotNull(message = "La descripcion no puede ser null")
	private String descripcion;
	
	@PrePersist
	public void prePersist(){
		if(descripcion != null){
			descripcion = descripcion.toUpperCase();
		}
	}
	
	@Builder
	public static Laboratorio target(String id, String descripcion){
		Laboratorio lab = new Laboratorio();
		lab.setId(id);
		lab.setDescripcion(descripcion);
		
		return lab;
	}
}
