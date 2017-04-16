package com.biotrends.assemblers.consumo;

import java.util.Date;

import com.biotrends.assemblers.EntityResource;
import com.biotrends.assemblers.item.ItemResource;
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
 *
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ConsumoResource", description = "The Consumo Resource Representation")
public class ConsumoResource extends EntityResource {

	private static final long serialVersionUID = 9133002845785302211L;

	@JsonProperty
	private Date fechaDescargo;
	
	@JsonProperty
	private String area;
	
	@JsonProperty
	private Double cantidad;
	
	@JsonProperty
	private ItemResource item;
	
	@JsonProperty
	private String usuario;
	
	@Builder
	public static ConsumoResource target(Date fechaDescargo, 
			String area, Double cantidad, ItemResource item, 
			String usuario){
		ConsumoResource consumo = new ConsumoResource();
		consumo.setFechaDescargo(fechaDescargo);
		consumo.setArea(area);
		consumo.setCantidad(cantidad);
		consumo.setItem(item);
		consumo.setUsuario(usuario);
		
		return consumo;
	}
}
