package com.biotrends.services.itemxproveedor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.biotrends.entities.itemxproveedor.ItemXProveedor;

/**
 * @author Oscar Malagon
 * @since 07/02/2017
 */
public interface ItemxProveedorService {

    Optional<ItemXProveedor> createOrUpdateItemxProveedor(ItemXProveedor itemxProveedor);

    Optional<ItemXProveedor> findByIdItemAndIdProveedor(String idItem, String idProveedor);
    
    Set<ItemXProveedor> findByIdProveedor(String idProveedor);
    
    Optional<ItemXProveedor> findById(String id);

    Page<ItemXProveedor> findAll(int page, int size);

    List<ItemXProveedor> findAll();

    Optional<ItemXProveedor> delete(String id);

}
