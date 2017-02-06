package com.biotrends.services.usuario;

import com.biotrends.entities.usuario.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * @author Oscar Malagon
 * @since 23/12/2016.
 */
public interface UsuarioService {

    Optional<Usuario> createOrUpdateItem(Usuario usuario);

    Optional<Usuario> findById(String id);

    List<Usuario> findAll();

    Optional<Usuario> delete(String id);
}
