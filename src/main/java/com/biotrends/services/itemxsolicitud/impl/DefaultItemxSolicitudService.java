package com.biotrends.services.itemxsolicitud.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biotrends.entities.itemxsolicitud.ItemXSolicitud;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.repositories.itemxsolicitud.ItemxSolicitudRepository;
import com.biotrends.services.itemxsolicitud.ItemxSolicitudService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Oscar Malagon
 * @since 09/02/2017
 */
@Slf4j
@Service
public class DefaultItemxSolicitudService implements ItemxSolicitudService {

	private final ItemxSolicitudRepository repository;
	
	public DefaultItemxSolicitudService(ItemxSolicitudRepository repository) {
		this.repository = repository;
	}


	@Override
	public Optional<ItemXSolicitud> findById(String id) {
		checkNotNull(id, "El id del itemxsolicitud no puede ser null");
		
		return Optional.ofNullable(repository.findOne(id));
	}


	@Override
	public Optional<ItemXSolicitud> delete(String id) {
		checkNotNull(id, "El id del itemxsolicitud no puede ser null");
		
		Optional<ItemXSolicitud> ixsFound = findById(id);
		
		if(ixsFound.isPresent()){
			repository.delete(id);
			
			return ixsFound;
		}
		
		throw new CommonBiotrendsRuntimeException("No se eliminó el itemxsolicitud con id [ " + id + "]");
	}

	@Override
	public Optional<ItemXSolicitud> createOrUpdateItemXSolicitud(ItemXSolicitud itemXSolicitud) {
		checkNotNull(itemXSolicitud, "El item no puede ser nulo");
		if(itemXSolicitud.getId() != null){
			Optional<ItemXSolicitud> itemEncontrado = findById(itemXSolicitud.getId());
			if(itemEncontrado.isPresent()){
				ItemXSolicitud item = itemEncontrado.get();
				item.setCantidadAprobada(itemXSolicitud.getCantidadAprobada());
				item.setCantidadSolicitada(itemXSolicitud.getCantidadSolicitada());
				item.setGenerado(itemXSolicitud.getGenerado());
				item.setIdItem(itemXSolicitud.getIdItem());
				item.setNumOrdenAsociado(itemXSolicitud.getNumOrdenAsociado());
				item.setProveedor(itemXSolicitud.getProveedor());
				
				return Optional.ofNullable(repository.saveAndFlush(item));
			}
		}
		
		return Optional.ofNullable(repository.saveAndFlush(itemXSolicitud));
	}


	@Override
	public List<ItemXSolicitud> getItemsYaAsociados(Long numeroOrden) {
		try {
			return repository.getItemsByNumeroOrden(numeroOrden);
		} catch (Exception ex) {
			log.error("No se encontraron items asociados al numero de orden [" + numeroOrden + "]");
			throw new CommonBiotrendsRuntimeException("No se encontraron items asociados al numero de orden [" + numeroOrden + "]",ex);
		}
	}	

}
