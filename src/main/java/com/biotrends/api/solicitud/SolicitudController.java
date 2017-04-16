package com.biotrends.api.solicitud;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biotrends.entities.solicitud.Solicitud;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Oscar Malagon
 * @since 06/02/2017.
 */
public interface SolicitudController {

    @ApiModelProperty(position = 1, required = true, value = "List of solicituds")
    @ApiOperation(value = "Get solicituds", notes = "Find all solicituds", response = Solicitud.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An Solicitud from given Id"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Solicitud Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<Solicitud>> getSolicitudes();

    @ApiModelProperty(position = 2, required = true, value = "Page of solicituds")
    @ApiOperation(value = "Get solicituds", notes = "Find all solicituds at the page", response = Solicitud.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An Solicitud from given Id"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Solicitud Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/{page}/{size}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Page<Solicitud>> getSolicitudes(@PathVariable final int page,
                                               @PathVariable final int size);

    @ApiModelProperty(position = 3, required = true, value = "Solicitud given its id")
    @ApiOperation(value = "Get solicitud given an id", notes = "Find the solicitud that match the given id", response = Solicitud.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An Solicitud from given Id"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Solicitud Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Solicitud> getSolicitudById(@PathVariable final String id);

    @ApiModelProperty(position = 4, required = true, value = "Deletes an solicitud given its id")
    @ApiOperation(value = "Delete solicitud", notes = "Delete solicitud that match the given id", response = Solicitud.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 410, message = "The solicitud has been deleted"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Solicitud Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/delete/{id}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Solicitud> deleteSolicitudById(@PathVariable final String id);

    @ApiModelProperty(position = 5, required = true, value = "Creates an solicitud")
    @ApiOperation(value = "Create solicitud", notes = "Creates an solicitud given its information", response = Solicitud.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "The solicitud has been created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Solicitud Not created"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Solicitud> createSolicitud(
        @ApiParam(value = "The solicitud object", required = true) @RequestBody(required = true)
            Solicitud solicitud);

    @ApiModelProperty(position = 6, required = true, value = "Solicitudes report in Excel")
    @ApiOperation(value = "Solicitudes report in Excel", notes = "Solicitudes report in Excel", response = String.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "The report path"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Solicitudes Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/report", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public void generateReport(HttpServletResponse response) throws IOException;
    
    @ApiModelProperty(position = 7, required = true, value = "List of solicitudes")
    @ApiOperation(value = "Get solicitudes by idSolicitante", notes = "Get solicitudes by idSolicitante", response = Solicitud.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An Solicitud from given Id"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Solicitud Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/solicitante/{idSolicitante}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<Solicitud>> getSolicitudesByIdSolicitante(@PathVariable final String idSolicitante);
}
