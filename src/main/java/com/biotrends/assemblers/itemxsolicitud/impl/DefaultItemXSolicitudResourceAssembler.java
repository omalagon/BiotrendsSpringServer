package com.biotrends.assemblers.itemxsolicitud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biotrends.assemblers.itemxsolicitud.ItemXSolicitudResource;
import com.biotrends.assemblers.itemxsolicitud.ItemXSolicitudResourceAssembler;
import com.biotrends.assemblers.proveedor.ProveedorResource;
import com.biotrends.assemblers.proveedor.ProveedorResourceAssembler;
import com.biotrends.entities.itemxsolicitud.ItemXSolicitud;
import com.biotrends.entities.proveedor.Proveedor;

/**
 * @author Oscar Malagon
 * @since 15/04/2017
 *
 */
@Service
public class DefaultItemXSolicitudResourceAssembler implements ItemXSolicitudResourceAssembler {

	private final ProveedorResourceAssembler proveedorAssembler;
	
	@Autowired
	public DefaultItemXSolicitudResourceAssembler(ProveedorResourceAssembler proveedorAssembler) {
		this.proveedorAssembler = proveedorAssembler;
	}
	
	@Override
	public ItemXSolicitudResource toResource(ItemXSolicitud entity) {
		if(entity != null){
			
			ProveedorResource proveedor = proveedorAssembler.toResource(entity.getProveedor());
			
			return ItemXSolicitudResource.builder()
					.idItem(entity.getIdItem())
					.cantidadSolicitada(entity.getCantidadSolicitada())
					.cantidadAprobada(entity.getCantidadAprobada())
					.proveedor(proveedor)
					.generado(entity.getGenerado())
					.numOrdenAsociado(entity.getNumOrdenAsociado())
					.build();
		}
		return null;
	}

	@Override
	public ItemXSolicitud fromResource(ItemXSolicitudResource resource) {
		
		if(resource != null){
			Proveedor proveedor = proveedorAssembler.fromResource(resource.getProveedor());
		
			return ItemXSolicitud.builder()
					.idItem(resource.getIdItem())
					.cantidadSolicitada(resource.getCantidadSolicitada())
					.cantidadAprobada(resource.getCantidadAprobada())
					.proveedor(proveedor)
					.generado(resource.getGenerado())
					.numOrdenAsociado(resource.getNumOrdenAsociado())
					.build();
		}
		return null;
	}

}
