package com.biotrends.assemblers.proveedor.impl;

import org.springframework.stereotype.Service;

import com.biotrends.assemblers.proveedor.ProveedorResource;
import com.biotrends.assemblers.proveedor.ProveedorResourceAssembler;
import com.biotrends.entities.proveedor.Proveedor;

/**
 * @author Oscar Malagon
 * @since 15/04/2017 
 */

@Service
public class DefaultProveedorResourceAssembler implements ProveedorResourceAssembler {

	@Override
	public ProveedorResource toResource(Proveedor entity) {
		if(entity != null){
			return ProveedorResource.builder()
					.nombre(entity.getNombre())
					.direccion(entity.getDireccion())
					.correo(entity.getCorreo())
					.telefono(entity.getTelefono())
					.fax(entity.getFax())
					.celular(entity.getCelular())
					.contacto(entity.getContacto())
					.ciudad(entity.getCiudad())
					.id(entity.getId())
					.build();
		}
		return null;
	}

	@Override
	public Proveedor fromResource(ProveedorResource resource) {
		if(resource != null){
			return Proveedor.builder()
					.nombre(resource.getNombre())
					.direccion(resource.getDireccion())
					.correo(resource.getCorreo())
					.telefono(resource.getTelefono())
					.fax(resource.getFax())
					.celular(resource.getCelular())
					.contacto(resource.getContacto())
					.ciudad(resource.getCiudad())
					.id(resource.getIdentifier())
					.build();
		}
		return null;
	}

}
