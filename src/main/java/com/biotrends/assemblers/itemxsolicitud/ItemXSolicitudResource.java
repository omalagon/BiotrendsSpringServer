package com.biotrends.assemblers.itemxsolicitud;
import com.biotrends.assemblers.EntityResource;
import com.biotrends.assemblers.proveedor.ProveedorResource;
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
@ApiModel(value = "ItemXSolicitudResource", description = "The itemxsolicitud Resource Representation")
public class ItemXSolicitudResource extends EntityResource {
	
	private static final long serialVersionUID = -1790172654238407107L;

	@JsonProperty
	private String idItem;
	
	@JsonProperty
	private Double cantidadSolicitada;
	
	@JsonProperty
	private Double cantidadAprobada;
	
	@JsonProperty
	private ProveedorResource proveedor;
	
	@JsonProperty
	private Boolean generado;
	
	@JsonProperty
	private Long numOrdenAsociado;
	
	@Builder
	public static ItemXSolicitudResource target(String idItem,
			Double cantidadSolicitada,
			Double cantidadAprobada,
			ProveedorResource proveedor, 
			Boolean generado,
			Long numOrdenAsociado){
		
		ItemXSolicitudResource item = new ItemXSolicitudResource();
		item.setIdItem(idItem);
		item.setCantidadSolicitada(cantidadSolicitada);
		item.setCantidadAprobada(cantidadAprobada);
		item.setProveedor(proveedor);
		item.setGenerado(generado);
		item.setNumOrdenAsociado(numOrdenAsociado);
		
		return item;
	}
	
}
