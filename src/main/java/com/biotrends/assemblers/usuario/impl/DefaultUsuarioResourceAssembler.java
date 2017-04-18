package com.biotrends.assemblers.usuario.impl;

import com.biotrends.assemblers.usuario.UsuarioResourceAssembler;
import com.biotrends.assemblers.usuario.UsuarioResource;
import com.biotrends.entities.usuario.Usuario;
import org.springframework.stereotype.Service;

/**
 * @author Oscar Malagon
 * @since 4/02/2017.
 */
@Service public class DefaultUsuarioResourceAssembler implements UsuarioResourceAssembler {

    @Override public Usuario fromResource(UsuarioResource resource) {
        if (resource != null) {
        	return Usuario.builder()
        			.id(resource.getIdentifier())
        			.nombre(resource.getNombre())
        			.password(resource.getPassword())
        			.correo(resource.getCorreo())
        			.laboratorio(resource.getLaboratorio())
        			.usuarioCreador(resource.getUsuarioCreador())
        			.build();
        }

        return null;
    }

    @Override public UsuarioResource toResource(Usuario entity) {
        if (entity != null) {
            return UsuarioResource.builder()
            		.id(entity.getId())
            		.nombre(entity.getNombre())
            		.correo(entity.getCorreo())
            		.laboratorio(entity.getLaboratorio())
            		.usuarioCreador(entity.getUsuarioCreador())
            		.build();
        }

        return null;
    }
}
