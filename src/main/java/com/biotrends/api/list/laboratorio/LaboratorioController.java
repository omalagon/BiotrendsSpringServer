package com.biotrends.api.list.laboratorio;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biotrends.assemblers.list.laboratorio.LaboratorioResource;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Oscar Malagon
 * @since 17/04/2017
 */
public interface LaboratorioController {

	@ApiModelProperty(position = 1, required = true, value = "List of laboratorios")
    @ApiOperation(value = "Get laboratorios", notes = "Find all laboratorios", response = LaboratorioResource.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "All laboratorios"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Laboratorios Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<LaboratorioResource>> getLaboratorio();

}
