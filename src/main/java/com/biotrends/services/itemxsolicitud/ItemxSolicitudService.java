package com.biotrends.services.itemxsolicitud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.biotrends.entities.itemxsolicitud.ItemXSolicitud;

/**
 * @author Oscar Malagon
 * @since 09/02/2017
 */
public interface ItemxSolicitudService {

    Optional<ItemXSolicitud> createOrUpdateItemxProveedor(ItemXSolicitud itemxProveedor);
    
    Optional<ItemXSolicitud> findById(String id);

    Page<ItemXSolicitud> findAll(int page, int size);

    List<ItemXSolicitud> findAll();

    Optional<ItemXSolicitud> delete(String id);

}
