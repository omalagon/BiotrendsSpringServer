package com.biotrends.services.solicitud;

import com.biotrends.entities.solicitud.Solicitud;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author Oscar Malagon
 * @since 06/02/2017
 */
public interface SolicitudService {

    Optional<Solicitud> createOrUpdateSolicitud(Solicitud Solicitud);

    Optional<Solicitud> findById(String id);

    Page<Solicitud> findAll(int page, int size);

    List<Solicitud> findAll();

    Optional<Solicitud> delete(String id);
}

