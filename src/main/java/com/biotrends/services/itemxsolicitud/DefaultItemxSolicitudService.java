/**
 * @author Oscar Malagon
 * @since 9/02/2017
 */
package com.biotrends.services.itemxsolicitud;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.biotrends.entities.itemxsolicitud.ItemXSolicitud;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.repositories.itemxsolicitud.ItemxSolicitudRepository;

/**
 * @author Oscar Malagon
 *
 */
@Service
public class DefaultItemxSolicitudService implements ItemxSolicitudService {

	private final ItemxSolicitudRepository repository;
	
	public DefaultItemxSolicitudService(ItemxSolicitudRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<ItemXSolicitud> createOrUpdateItemxProveedor(ItemXSolicitud itemxProveedor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ItemXSolicitud> findById(String id) {
		checkNotNull(id, "El id del itemxsolicitud no puede ser null");
		
		return Optional.ofNullable(repository.findOne(id));
	}

	@Override
	public Page<ItemXSolicitud> findAll(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemXSolicitud> findAll() {
		// TODO Auto-generated method stub
		return null;
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

	

}