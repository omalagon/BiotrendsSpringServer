package com.biotrends.api.consumo.excepciones;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biotrends.entities.consumo.excepciones.TablaExcepcion;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Oscar Malagon
 * @since 11/04/2017
 */

public interface TablaExcepcionesController {

	@ApiModelProperty(position = 1, required = true, value = "List of consumos")
    @ApiOperation(value = "Get excepciones en consumos", notes = "Get excepciones en consumos", response = TablaExcepcion.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "All excepciones consumos"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Consumo Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<TablaExcepcion>> getExcepcionesConsumos();
	
	@ApiModelProperty(position = 2, required = true, value = "Creates a registry")
    @ApiOperation(value = "Creates a registry", notes = "Creates a registry", response = TablaExcepcion.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "The registry has been created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Consumo Not created"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<TablaExcepcion> createConsumo(
        @ApiParam(value = "The registry object", required = true) @RequestBody(required = true)
        TablaExcepcion tablaExcepcion);
	
	@ApiModelProperty(position = 3, required = true, value = "Deletes a registry given the name of the tabla excepcion")
    @ApiOperation(value = "Delete registry", notes = "Delete consumo that match the given id", response = TablaExcepcion.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 410, message = "The registry has been deleted"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Consumo Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/delete/{nombreExcepcion}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<TablaExcepcion> deleteConsumoById(@PathVariable final String nombreExcepcion);
	
}
