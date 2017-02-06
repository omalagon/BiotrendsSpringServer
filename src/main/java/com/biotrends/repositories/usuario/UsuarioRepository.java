package com.biotrends.repositories.usuario;

import org.springframework.stereotype.Repository;

import com.biotrends.entities.usuario.Usuario;
import com.biotrends.repositories.EntityRepository;

/**
 * @author Oscar Malagon
 * @since 5/12/2016.
 */
@Repository
public interface UsuarioRepository extends EntityRepository<Usuario>{
}
