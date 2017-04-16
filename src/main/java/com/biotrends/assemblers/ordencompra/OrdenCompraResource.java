package com.biotrends.assemblers.ordencompra;

import com.biotrends.assemblers.EntityResource;
import com.biotrends.assemblers.usuario.UsuarioResource;
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
@ApiModel(value = "OrdenCompraResource", description = "The ordencompra Resource Representation")
public class OrdenCompraResource extends EntityResource {
	
	private static final long serialVersionUID = -4062293323439283493L;

	@JsonProperty
	Long numeroOrden;
	
	@JsonProperty
	private String observaciones;
	
	@JsonProperty
	private UsuarioResource usuario;
	
	@Builder
	public static OrdenCompraResource target(Long numeroOrden,
			String observaciones, 
			UsuarioResource usuario){
		
		OrdenCompraResource orden = new OrdenCompraResource();
		orden.setNumeroOrden(numeroOrden);
		orden.setObservaciones(observaciones);
		orden.setUsuario(usuario);
		
		return orden;
	}
}
