package com.biotrends.services.itemxproveedor.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.biotrends.entities.item.Item;
import com.biotrends.entities.itemxproveedor.ItemXProveedor;
import com.biotrends.entities.proveedor.Proveedor;
import com.biotrends.services.item.ItemService;
import com.biotrends.services.itemxproveedor.ItemxProveedorService;
import com.biotrends.services.proveedor.ProveedorService;

/**
 * @author Oscar Malagon
 * @since 7/02/2017
 */
@Service
public class DefaultItemxProveedorService implements ItemxProveedorService {

	private final ItemService itemService;
	private final ProveedorService provService;
	
	@Autowired
	public DefaultItemxProveedorService(ItemService itemService, ProveedorService provService ) {
		this.itemService = itemService;
		this.provService = provService;
	}

	@Override
	public Optional<ItemXProveedor> createOrUpdateItemxProveedor(ItemXProveedor itemxProveedor) {
		checkNotNull(itemxProveedor.getItem(), "El item no puede ser nulo");
		checkNotNull(itemxProveedor.getItem().getId(), "El id del item no puede ser nulo");
		checkNotNull(itemxProveedor.getProveedor(), "El proveedor no puede ser nulo");
		checkNotNull(itemxProveedor.getProveedor().getId(), "El id del proveedor no puede ser nulo");
		
		Optional<Item> itemFound = itemService.findById(itemxProveedor.getItem().getId());
		Optional<Proveedor> proveedorFound = provService.findById(itemxProveedor.getProveedor().getId());
		if(itemFound.isPresent() && proveedorFound.isPresent()){
			
		}
		
		
		return null;
	}

	@Override
	public Optional<ItemXProveedor> findByIdItemAndIdProveedor(String idItem, String idProveedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ItemXProveedor> findAll(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemXProveedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ItemXProveedor> delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
