package com.biotrends.services.itemxsolicitud;

import java.util.Optional;

import com.biotrends.entities.itemxsolicitud.ItemXSolicitud;

/**
 * @author Oscar Malagon
 * @since 09/02/2017
 */
public interface ItemxSolicitudService {

    
    Optional<ItemXSolicitud> findById(String id);

    Optional<ItemXSolicitud> delete(String id);

}
