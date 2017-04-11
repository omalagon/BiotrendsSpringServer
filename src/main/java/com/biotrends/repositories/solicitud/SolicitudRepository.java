package com.biotrends.repositories.solicitud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biotrends.entities.solicitud.Solicitud;
import com.biotrends.repositories.EntityRepository;

/**
 * @author Oscar Malagon
 * @since 06/02/2016.
 */
@Repository
public interface SolicitudRepository extends EntityRepository<Solicitud>{
	
	@Query("SELECT MAX(solicitud.idSolicitud) FROM Solicitud solicitud")
	Long getLastSolicitud();
}
