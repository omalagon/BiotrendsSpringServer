package com.biotrends.repositories.consumo.excepciones;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biotrends.entities.consumo.excepciones.TablaExcepcion;
import com.biotrends.repositories.EntityRepository;

/**
 * @author Oscar Malagon
 *
 */
@Repository
public interface TablaExcepcionRepository extends EntityRepository<TablaExcepcion>{

	@Query("SELECT tabla from TablaExcepcion tabla where tabla.nombreExcepcion = :nombreExcepcion")
	TablaExcepcion getTablaExcepcionByNombre(@Param("nombreExcepcion") final String nombreExcepcion);
}
