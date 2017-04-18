package com.biotrends.assemblers.list.laboratorio;

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
 * @since 17/04/2017
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LaboratorioResource", description = "The Laboratorio Resource Representation")
public class LaboratorioResource extends EntityResource {

	private static final long serialVersionUID = -1232179116789269625L;

	@JsonProperty
	private String descripcion;
	
	@Builder
	public static LaboratorioResource target(String id, String descripcion){
		LaboratorioResource lab = new LaboratorioResource();
		lab.setIdentifier(id);
		lab.setDescripcion(descripcion);
		
		return lab;
	}
}
