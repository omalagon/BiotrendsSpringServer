package com.biotrends.api.oldconsumo;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biotrends.entities.oldconsumo.OldConsumoDTO;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Oscar Malagon
 * @since 27/01/2017.
 */
public interface OldConsumoController {

    @ApiModelProperty(position = 1, required = true, value = "List of consumos")
    @ApiOperation(value = "Get consumos", notes = "Find all consumos", response = OldConsumoDTO.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An Item from given Id"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Consumo Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<OldConsumoDTO>> getConsumos();
}
