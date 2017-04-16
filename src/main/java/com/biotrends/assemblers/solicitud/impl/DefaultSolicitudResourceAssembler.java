package com.biotrends.assemblers.solicitud.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biotrends.assemblers.itemxsolicitud.ItemXSolicitudResource;
import com.biotrends.assemblers.itemxsolicitud.ItemXSolicitudResourceAssembler;
import com.biotrends.assemblers.solicitud.SolicitudResource;
import com.biotrends.assemblers.solicitud.SolicitudResourceAssembler;
import com.biotrends.entities.itemxsolicitud.ItemXSolicitud;
import com.biotrends.entities.solicitud.Solicitud;

/**
 * @author Oscar Malagon
 * @since 15/04/2017
 */
@Service
public class DefaultSolicitudResourceAssembler implements SolicitudResourceAssembler {

	private final ItemXSolicitudResourceAssembler itmxsolAssembler;
	
	@Autowired
	public DefaultSolicitudResourceAssembler(ItemXSolicitudResourceAssembler itmxsolAssembler) {
		this.itmxsolAssembler = itmxsolAssembler;
	}
	
	@Override
	public SolicitudResource toResource(Solicitud entity) {
				
		if(entity != null){
			
			Set<ItemXSolicitudResource> items = new HashSet<>();
			for (ItemXSolicitud itm : entity.getItmxsol()) {
				items.add(itmxsolAssembler.toResource(itm));
			}
			
			return SolicitudResource.builder()
					.fechaSolicitud(entity.getFechaSolicitud())
					.observaciones(entity.getObservaciones())
					.esRevisado(entity.getEsRevisado())
					.solicitante(entity.getSolicitante())
					.auxiliarOficina(entity.getAuxiliarOficina())
					.itmxsol(items)
					.idSolicitud(entity.getIdSolicitud())
					.build();
		}
		return null;
	}

	@Override
	public Solicitud fromResource(SolicitudResource resource) {
		if(resource != null){
			Set<ItemXSolicitud> items = new HashSet<>();
			for (ItemXSolicitudResource itm : resource.getItmxsol()) {
				items.add(itmxsolAssembler.fromResource(itm));
			}
			
			return Solicitud.builder()
					.fechaSolicitud(resource.getFechaSolicitud())
					.observaciones(resource.getObservaciones())
					.esRevisado(resource.getEsRevisado())
					.solicitante(resource.getSolicitante())
					.auxiliarOficina(resource.getAuxiliarOficina())
					.itmxsol(items)
					.idSolicitud(resource.getIdSolicitud())
					.build();
		}
		return null;
	}

}
