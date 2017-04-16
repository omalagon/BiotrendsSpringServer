package com.biotrends.services.excepcion.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biotrends.entities.consumo.excepciones.TablaExcepcion;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.repositories.consumo.excepciones.TablaExcepcionRepository;
import com.biotrends.services.excepcion.TablaExcepcionService;

/**
 * @author Oscar Malagon
 * @since 11/04/2017
 */

@Service
public class DefaultTablaExcepcionService implements TablaExcepcionService {

	private final TablaExcepcionRepository repository;
	
	@Autowired
	public DefaultTablaExcepcionService(TablaExcepcionRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<TablaExcepcion> getAll() {
		return repository.findAll();
	}

	@Transactional
	@Override
	public TablaExcepcion createTablaExcepcion(TablaExcepcion tablaExcepcion) {
		checkNotNull(tablaExcepcion.getNombreExcepcion(), "El nombre del item es requerido");
		Optional<TablaExcepcion> tablaExcepcionEncontrada = getTablaExcepcionByNombreExcepcion(tablaExcepcion.getNombreExcepcion());
		
		if(!tablaExcepcionEncontrada.isPresent()){
			return repository.save(tablaExcepcion);			
		}
		
		throw new CommonBiotrendsRuntimeException("Ya existe el registro con nombreExcepcion [" + tablaExcepcion.getNombreExcepcion() + "]");		
	}

	@Override
	public TablaExcepcion deleteTablaExcepcion(String nombreExcepcion) {
		checkNotNull(nombreExcepcion, "El nombre del item es requerido");
		Optional<TablaExcepcion> tablaExcepcion = getTablaExcepcionByNombreExcepcion(nombreExcepcion);
		
		if(tablaExcepcion.isPresent()){
			repository.delete(tablaExcepcion.get().getId());
			
			return tablaExcepcion.get();
		}
		
		throw new CommonBiotrendsRuntimeException("Error eliminando el registro con nombreExcepcion [" + nombreExcepcion + "]");
	}
	
	@Override
	public Optional<TablaExcepcion> getTablaExcepcionByNombreExcepcion(String nombreExcepcion){
		checkNotNull(nombreExcepcion, "El nombre del item es requerido");
		
		return Optional.ofNullable(repository.getTablaExcepcionByNombre(nombreExcepcion));
	}

}
