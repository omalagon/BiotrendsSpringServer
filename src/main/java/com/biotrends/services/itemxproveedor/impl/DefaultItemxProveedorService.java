package com.biotrends.services.itemxproveedor.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biotrends.entities.item.Item;
import com.biotrends.entities.itemxproveedor.ItemXProveedor;
import com.biotrends.entities.proveedor.Proveedor;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.repositories.itemxproveedor.ItemxProveedorRepository;
import com.biotrends.services.item.ItemService;
import com.biotrends.services.itemxproveedor.ItemxProveedorService;
import com.biotrends.services.proveedor.ProveedorService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Oscar Malagon
 * @since 7/02/2017
 */
@Service
@Slf4j
public class DefaultItemxProveedorService implements ItemxProveedorService {

	private final ItemService itemService;
	private final ProveedorService provService;
	private final ItemxProveedorRepository repository;
	
	@Autowired
	public DefaultItemxProveedorService(ItemService itemService, ProveedorService provService, ItemxProveedorRepository repository ) {
		this.itemService = itemService;
		this.provService = provService;
		this.repository = repository;
	}

	@Override
	public Optional<ItemXProveedor> createOrUpdateItemxProveedor(ItemXProveedor itemxProveedor) {
		checkNotNull(itemxProveedor.getItem(), "El id del item no puede ser nulo");
		checkNotNull(itemxProveedor.getProveedor(), "El id del proveedor no puede ser nulo");
		
		Optional<Item> itemFound = itemService.findById(itemxProveedor.getItem());
		Optional<Proveedor> proveedorFound = provService.findById(itemxProveedor.getProveedor());
		if(itemFound.isPresent() && proveedorFound.isPresent()){
			Optional<ItemXProveedor> ixpFound = findByIdItemAndIdProveedor(itemxProveedor.getItem(), itemxProveedor.getProveedor());
			if(ixpFound.isPresent()){
				ItemXProveedor ixpAux = ixpFound.get();
				ixpAux.setPrecio(itemxProveedor.getPrecio());
				
				return Optional.ofNullable(repository.saveAndFlush(ixpAux));
			}else{
				return Optional.ofNullable(repository.saveAndFlush(itemxProveedor));
			}
		}
		
		
		return null;
	}

	@Override
	public Optional<ItemXProveedor> findByIdItemAndIdProveedor(String idItem, String idProveedor) {
		checkNotNull(idItem, "El id del item no puede ser null");
		checkNotNull(idProveedor, "El id del proveedor no puede ser null");
		
		return Optional.ofNullable(repository.findByItsIds(idItem, idProveedor));
	}

	@Override
	public Optional<ItemXProveedor> findById(String id){
		checkNotNull(id, "El id del item no puede ser null");
		
		return Optional.ofNullable(repository.findOne(id));
	}
	
	@Override
	public Set<ItemXProveedor> findByIdProveedor(String idProveedor) {
		checkNotNull(idProveedor, "El id del proveedor no puede ser null");
		
		return repository.findByIdProveedor(idProveedor);
	}
	
	@Override
	public Page<ItemXProveedor> findAll(int page, int size) {
		checkNotNull(page, "La página no puede ser null");
        checkNotNull(size, "El tamaño de la pagina no puede ser null");
        checkArgument(page >= 0, "La página no puede ser negativa");
        checkArgument(size >= 0, "El tamaño de la página no puede ser negativa");

        try {
            Pageable pageable = new PageRequest(page, size);
            return repository.findAll(pageable);
        } catch (Exception ex) {
            log.error("Error buscando las solicitudes", ex);
            throw new EntityNotFoundException();
        }
	}

	@Override
	public List<ItemXProveedor> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<ItemXProveedor> delete(String id) {
		checkNotNull(id, "El id del item no puede ser null");
		
		Optional<ItemXProveedor> ixp = findById(id);
		if(ixp.isPresent()){
			repository.delete(id);
			
			return ixp;
		}
		
		throw new CommonBiotrendsRuntimeException("No encontrado");
	}

}
