package com.biotrends.services.proveedor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.biotrends.entities.proveedor.Proveedor;

/**
 * @author Oscar Malagon
 * @since 23/12/2016.
 */
public interface  ProveedorService {

    Optional<Proveedor> createOrUpdateProveedor(Proveedor proveedor);

    Optional<Proveedor> findById(String id);

    List<Proveedor> findAll();
    
    Page<Proveedor> findAll(int page, int number);

    Optional<Proveedor> delete(String id);
}
