package com.biotrends.services.proveedor.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biotrends.entities.proveedor.Proveedor;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.repositories.proveedor.ProveedorRepository;
import com.biotrends.services.proveedor.ProveedorService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Oscar Malagon
 * @since 22/12/2016.
 */
@Slf4j 
@Service 
public class DefaultProveedorService implements ProveedorService {

    private final ProveedorRepository repository;

    @Autowired
    public DefaultProveedorService(ProveedorRepository repository) {
        this.repository = repository;
    }

    @Override public Optional<Proveedor> createOrUpdateProveedor(Proveedor proveedor) {
        try{
        	if (proveedor.getId() != null) {
                Optional<Proveedor> proveedorEncontrado = findById(proveedor.getId());
                if (proveedorEncontrado.isPresent()) {
                    Proveedor updatedProveedor = proveedorEncontrado.get();
                    updatedProveedor.setCelular(proveedor.getCelular());
                    updatedProveedor.setCiudad(proveedor.getCiudad());
                    updatedProveedor.setContacto(proveedor.getContacto());
                    updatedProveedor.setCorreo(proveedor.getCorreo());
                    updatedProveedor.setDireccion(proveedor.getDireccion());
                    updatedProveedor.setEvaluaciones(proveedor.getEvaluaciones());
                    updatedProveedor.setFax(proveedor.getFax());
                    updatedProveedor.setItemsXProveedor(proveedor.getItemsXProveedor());
                    updatedProveedor.setNombre(proveedor.getNombre());
                    updatedProveedor.setTelefono(proveedor.getTelefono());
                    
                    return Optional.ofNullable(repository.saveAndFlush(updatedProveedor));
                } else {
                	return Optional.ofNullable(repository.saveAndFlush(proveedor));
                }
            }

            return Optional.ofNullable(repository.saveAndFlush(proveedor));
        }catch (Exception e) {
			log.error("Ocurríó un error creando/actualizando el proveedor");
			throw new CommonBiotrendsRuntimeException("Ocurríó un error creando/actualizando el proveedor");
		}
    	
        
    }

    @Override public Optional<Proveedor> findById(String id) {
        checkNotNull(id, "El id del ítem no puede ser nulo");

        try {
        	Proveedor proveedor = repository.findOne(id);

            return Optional.ofNullable(proveedor);
        } catch (Exception ex) {
            log.error("Error buscando el proveedor con id [" + id + "]", ex);
            throw new EntityNotFoundException();
        }
    }

    @Override public Page<Proveedor> findAll(int page, int size) {
        checkNotNull(page, "La página no puede ser null");
        checkNotNull(size, "El tamaño de la pagina no puede ser null");
        checkArgument(page >= 0, "La página no puede ser negativa");
        checkArgument(size >= 0, "El tamaño de la página no puede ser negativa");

        try {
            Pageable pageable = new PageRequest(page, size);
            return repository.findAll(pageable);
        } catch (Exception ex) {
            log.error("Error buscando los proveedores", ex);
            throw new EntityNotFoundException();
        }
    }

    @Override public List<Proveedor> findAll() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            log.error("Error buscando los proveedores", ex);
            throw new EntityNotFoundException();
        }
    }

    @Override public Optional<Proveedor> delete(String id) {
        Optional<Proveedor> proveedor = findById(id);
        try {
            if(proveedor.isPresent()){
                repository.delete(id);

                return proveedor;
            }

            log.error("Error buscando item con id [" + id + "]");
            throw new CommonBiotrendsRuntimeException("Error buscando el proveedor con id [" + id + "]");
        } catch (Exception ex) {
            log.error("Error eliminando el item con id [" + id + "]", ex);
            throw new CommonBiotrendsRuntimeException("Error eliminando el proveedor con id [" + id + "]", ex);
        }
    }

}
