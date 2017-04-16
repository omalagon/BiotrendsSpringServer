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

            Usuario usuario = resource.getUsuario();
            usuario.setUsuarioCreador(resource.getIdUsuarioCreador());

            return usuario;
        }

        return null;
    }

    @Override public UsuarioResource toResource(Usuario entity) {
        if (entity != null) {
            return UsuarioResource.builder().usuario(entity)
                .idUsuarioCreador(entity.getUsuarioCreador()).build();
        }

        return null;
    }
}
