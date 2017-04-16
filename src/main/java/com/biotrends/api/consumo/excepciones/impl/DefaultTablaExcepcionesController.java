package com.biotrends.api.consumo.excepciones.impl;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.biotrends.api.consumo.excepciones.TablaExcepcionesController;
import com.biotrends.entities.consumo.excepciones.TablaExcepcion;
import com.biotrends.services.excepcion.TablaExcepcionService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.GONE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Oscar Malagon
 * @since 11/04/2017
 */

@RestController
@Api(value = "api-excepciones", description = "Excepciones MVC EndPoint", produces = APPLICATION_HAL_JSON_VALUE)
@RequestMapping(value = "/api/tablaexcepciones", produces = APPLICATION_HAL_JSON_VALUE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DefaultTablaExcepcionesController implements TablaExcepcionesController {

	private final TablaExcepcionService service;
	
	@Autowired
	public DefaultTablaExcepcionesController(TablaExcepcionService service) {
		this.service = service;
	}
	
	@Override
	@RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<List<TablaExcepcion>> getExcepcionesConsumos() {
		
		List<TablaExcepcion> excepciones = service.getAll();
		return new ResponseEntity<>(excepciones, OK);
	}

	@Override
	@RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<TablaExcepcion> createConsumo(
    		@ApiParam(value = "The tabla excepcion object", required = true) @RequestBody(required = true)
    		TablaExcepcion tablaExcepcion) {
		
		return new ResponseEntity<>(service.createTablaExcepcion(tablaExcepcion), OK);
	}

	@Override
    @RequestMapping(value = "/delete/{nombreExcepcion}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<TablaExcepcion> deleteConsumoById(@PathVariable final String nombreExcepcion) {
		return new ResponseEntity<>(service.deleteTablaExcepcion(nombreExcepcion), GONE);
	}

}
