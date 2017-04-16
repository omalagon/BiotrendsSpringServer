package com.biotrends.assemblers.ordencompra.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biotrends.assemblers.ordencompra.OrdenCompraResource;
import com.biotrends.assemblers.ordencompra.OrdenCompraResourceAssembler;
import com.biotrends.assemblers.usuario.UsuarioResource;
import com.biotrends.assemblers.usuario.UsuarioResourceAssembler;
import com.biotrends.entities.ordencompra.OrdenCompra;
import com.biotrends.entities.usuario.Usuario;

/**
 * @author Oscar Malagon
 * @since 15/04/2017
 */
@Service
public class DefaultOrdenCompraResourceAssembler implements OrdenCompraResourceAssembler {

	private final UsuarioResourceAssembler usuarioAssembler;
	
	@Autowired
	public DefaultOrdenCompraResourceAssembler(UsuarioResourceAssembler usuarioAssembler) {
		this.usuarioAssembler = usuarioAssembler;
	}
	
	@Override
	public OrdenCompraResource toResource(OrdenCompra entity) {
		if(entity != null){
			
			UsuarioResource usuario = usuarioAssembler.toResource(entity.getUsuario());
			
			return OrdenCompraResource.builder()
					.numeroOrden(entity.getNumeroOrden())
					.observaciones(entity.getObservaciones())
					.usuario(usuario)
					.build();
		}
		return null;
	}

	@Override
	public OrdenCompra fromResource(OrdenCompraResource resource) {
		if(resource != null){
			
			Usuario usuario = usuarioAssembler.fromResource(resource.getUsuario());
			
			return OrdenCompra.builder()
					.numeroOrden(resource.getNumeroOrden())
					.observaciones(resource.getObservaciones())
					.usuario(usuario)
					.build();
		}
		return null;
	}

}
