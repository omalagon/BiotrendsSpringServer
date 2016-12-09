package com.biotrends.repositories.usuario;

import com.biotrends.entities.usuario.Usuario;
import com.biotrends.repositories.EntityRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Oscar Malagon
 * @since 5/12/2016.
 */
@Repository
public interface UsuarioRepository extends EntityRepository<Usuario>{
}
