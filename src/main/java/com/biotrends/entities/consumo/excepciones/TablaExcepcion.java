package com.biotrends.entities.consumo.excepciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.biotrends.entities.BiotrendsBaseEntity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Oscar Malagon
 * @since 11/04/2017
 */
@Data
@Entity
@Table(name = "BIO_CONSUMO_EXCEPCIONES")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TablaExcepcion extends BiotrendsBaseEntity{

	private static final long serialVersionUID = 5918131845251177663L;
	
	@Column(name = "CONS_EXC_NOM_ITEM")
	@NotNull
	private String nombreExcepcion;
	
	@Builder
	public static TablaExcepcion target(String nombreExcepcion){
		TablaExcepcion tablaExcepcion = new TablaExcepcion();
		tablaExcepcion.setNombreExcepcion(nombreExcepcion);
		
		return tablaExcepcion;		
	}
	
}
