package com.biotrends.repositories.usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biotrends.entities.usuario.Usuario;
import com.biotrends.repositories.EntityRepository;

/**
 * @author Oscar Malagon
 * @since 5/12/2016.
 */
@Repository
public interface UsuarioRepository extends EntityRepository<Usuario>{
	
	@Query("SELECT usuario FROM Usuario usuario where usuario.id = :id and usuario.password = :password")
	Usuario findByIdAndPassword(@Param("id") final String id, @Param("password") final String password);
}
