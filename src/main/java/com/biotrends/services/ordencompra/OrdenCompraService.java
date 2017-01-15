package com.biotrends.services.ordencompra;

import com.biotrends.entities.ordencompra.OrdenCompra;

import java.util.List;
import java.util.Optional;

/**
 * @author Oscar Malagon
 * @since 23/12/2016.
 */
public interface OrdenCompraService {

    Optional<OrdenCompra> createOrUpdateItem(OrdenCompra ordenCompra);

    Optional<OrdenCompra> findById(String id);

    List<OrdenCompra> findAll();

    Optional<OrdenCompra> delete(OrdenCompra ordenCompra);
}
