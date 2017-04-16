package com.biotrends.repositories.solicitud;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	
	@Query("SELECT solicitud FROM Solicitud solicitud where solicitud.solicitante = :idSolicitante")
	List<Solicitud> findSolicitudesByIdSolicitante(@Param("idSolicitante") final String idSolicitante);
}
