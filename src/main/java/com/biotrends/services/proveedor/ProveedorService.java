package com.biotrends.services.proveedor;

import com.biotrends.entities.proveedor.Proveedor;

import java.util.List;
import java.util.Optional;

/**
 * @author Oscar Malagon
 * @since 23/12/2016.
 */
public interface  ProveedorService {

    Optional<Proveedor> createOrUpdateItem(Proveedor proveedor);

    Optional<Proveedor> findById(String id);

    List<Proveedor> findAll();

    Optional<Proveedor> delete(Proveedor proveedor);
}
