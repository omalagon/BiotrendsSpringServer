package com.biotrends.services.ordencompra;

import com.biotrends.entities.itemxsolicitud.ItemXSolicitud;
import com.biotrends.entities.ordencompra.OrdenCompra;

import java.util.List;
import java.util.Optional;

/**
 * @author Oscar Malagon
 * @since 23/12/2016.
 */
public interface OrdenCompraService {

    Optional<OrdenCompra> createOrUpdateOrdenCompra(OrdenCompra ordenCompra, List<ItemXSolicitud> itemsSolicitud);

    Optional<OrdenCompra> findByNumeroOrden(Long NumeroOrden);

    List<OrdenCompra> findAll();

}
