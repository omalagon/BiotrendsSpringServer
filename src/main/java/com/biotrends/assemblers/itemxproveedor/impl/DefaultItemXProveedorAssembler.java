package com.biotrends.assemblers.itemxproveedor.impl;

import org.springframework.stereotype.Service;

import com.biotrends.assemblers.itemxproveedor.ItemXProveedorAssembler;
import com.biotrends.assemblers.itemxproveedor.ItemXProveedorResource;
import com.biotrends.entities.itemxproveedor.ItemXProveedor;

/**
 * @author Oscar Malagon
 * @since 15/04/2017
 */
@Service
public class DefaultItemXProveedorAssembler implements ItemXProveedorAssembler {

	@Override
	public ItemXProveedorResource toResource(ItemXProveedor entity) {
		if(entity != null){
			return ItemXProveedorResource.builder()
					.item(entity.getItem())
					.proveedor(entity.getProveedor())
					.precio(entity.getPrecio())
					.build();
		}
		return null;
	}

	@Override
	public ItemXProveedor fromResource(ItemXProveedorResource resource) {
		if(resource != null){
			return ItemXProveedor.builder()
					.item(resource.getItem())
					.proveedor(resource.getProveedor())
					.precio(resource.getPrecio())
					.build();
		}
		return null;
	}

}
