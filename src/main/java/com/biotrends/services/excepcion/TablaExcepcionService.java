package com.biotrends.services.excepcion;

import java.util.List;
import java.util.Optional;

import com.biotrends.entities.consumo.excepciones.TablaExcepcion;

/**
 * @author Oscar Malagon
 * @since 11/04/2017
 */
public interface TablaExcepcionService{

	List<TablaExcepcion> getAll();
	
	TablaExcepcion createTablaExcepcion(TablaExcepcion tablaExcepcion);
	
	TablaExcepcion deleteTablaExcepcion(String nombreExcepcion);
	
	Optional<TablaExcepcion> getTablaExcepcionByNombreExcepcion(String nombreExcepcion);
}
