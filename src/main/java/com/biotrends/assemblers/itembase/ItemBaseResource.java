package com.biotrends.assemblers.itembase;

import com.biotrends.assemblers.EntityResource;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Oscar Malagon
 * @since 14/04/2017
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ItemBaseResource", description = "The itembase Resource Representation")
public class ItemBaseResource extends EntityResource{

	private static final long serialVersionUID = -8527622674028399731L;

	@JsonProperty
	private String inventario;
	
	@JsonProperty
	private String descripcion;
	
	@JsonProperty
	private String presentacion;
	
	@JsonProperty
	private Double cantidad;
	
	@JsonProperty
	private Double precio;
	
	@JsonProperty
	private Boolean cCalidad;
	
	@JsonProperty
	private Boolean cEsp;
	
	@Builder
	public static ItemBaseResource target(String inventario,
			String descripcion, 
			String presentacion,
			Double cantidad, 
			Double precio,
			Boolean cCalidad,
			Boolean cEsp){
		ItemBaseResource item = new ItemBaseResource();
		item.setInventario(inventario);
		item.setDescripcion(descripcion);
		item.setPresentacion(presentacion);
		item.setCantidad(cantidad);
		item.setPrecio(precio);
		item.setCCalidad(cCalidad);
		item.setCEsp(cEsp);
		
		return item;
	}
}
