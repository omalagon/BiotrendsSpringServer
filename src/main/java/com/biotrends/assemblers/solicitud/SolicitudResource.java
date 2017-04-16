package com.biotrends.assemblers.solicitud;

import java.util.Date;
import java.util.Set;

import com.biotrends.assemblers.EntityResource;
import com.biotrends.assemblers.itemxsolicitud.ItemXSolicitudResource;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Oscar Malagon
 * @since 4/02/2017.
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SolicitudResource", description = "The Solicitud Resource Representation")
public class SolicitudResource extends EntityResource {
	
	private static final long serialVersionUID = -5554947754037693989L;

	@JsonProperty
	private Long idSolicitud;

	@JsonProperty
	private Date fechaSolicitud;
	
	@JsonProperty
	private String observaciones;
	
	@JsonProperty
	private Boolean esRevisado;
	
	@JsonProperty
	private String solicitante;
	
	@JsonProperty
	private String auxiliarOficina;
	
	@JsonProperty
	Set<ItemXSolicitudResource> itmxsol;
	
	@Builder
	public static SolicitudResource target(Long idSolicitud,
    		Date fechaSolicitud,
    		String observaciones, 
    		Boolean esRevisado, 
    		String solicitante, 
    		String auxiliarOficina,
    		Set<ItemXSolicitudResource> itmxsol){

		SolicitudResource solicitud = new SolicitudResource();
		solicitud.setIdSolicitud(idSolicitud);
		solicitud.setFechaSolicitud(fechaSolicitud);
		solicitud.setObservaciones(observaciones);
		solicitud.setEsRevisado(esRevisado);
		solicitud.setSolicitante(solicitante);
		solicitud.setAuxiliarOficina(auxiliarOficina);
		solicitud.setItmxsol(itmxsol);
		
		return solicitud;
	}
}
