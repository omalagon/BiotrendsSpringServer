package com.biotrends.assemblers.itembase.impl;

import org.springframework.stereotype.Service;

import com.biotrends.assemblers.itembase.ItemBaseAssembler;
import com.biotrends.assemblers.itembase.ItemBaseResource;
import com.biotrends.entities.item.ItemBase;

/**
 * @author Oscar Malagon
 * @since 15/04/2017
 *
 */
@Service
public class DefaultItemBaseResourceAssembler implements ItemBaseAssembler<ItemBase, ItemBaseResource> {

	@Override
	public ItemBaseResource toResource(ItemBase entity) {
		if(entity != null){
			return ItemBaseResource.builder()
					.inventario(entity.getInventario())
					.descripcion(entity.getDescripcion())
					.presentacion(entity.getPresentacion())
					.cantidad(entity.getCantidad())
					.precio(entity.getPrecio())
					.cCalidad(entity.getCCalidad())
					.cEsp(entity.getCEsp())
					.build();
		}
		return null;
	}

	@Override
	public ItemBase fromResource(ItemBaseResource resource) {
		if(resource != null){
			return ItemBase.builder()
					.inventario(resource.getInventario())
					.descripcion(resource.getDescripcion())
					.presentacion(resource.getPresentacion())
					.cantidad(resource.getCantidad())
					.precio(resource.getPrecio())
					.cCalidad(resource.getCCalidad())
					.cEsp(resource.getCEsp())
					.build();
		}
		return null;
	}

}
