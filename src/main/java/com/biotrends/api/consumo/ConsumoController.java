package com.biotrends.api.consumo;

import com.biotrends.entities.consumo.Consumo;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Oscar Malagon
 * @since 05/02/2017.
 */
public interface ConsumoController {

    @ApiModelProperty(position = 1, required = true, value = "List of consumos")
    @ApiOperation(value = "Get consumos", notes = "Find all consumos", response = Consumo.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "All consumos"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Consumo Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<Consumo>> getConsumos();

    @ApiModelProperty(position = 2, required = true, value = "Page of consumos")
    @ApiOperation(value = "Get consumos", notes = "Find all consumos at the page", response = Consumo.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An Consumo from given page and size"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Consumo Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Page<Consumo>> getConsumos(@PathVariable final int page,
        @PathVariable final int size);

    @ApiModelProperty(position = 3, required = true, value = "Consumo given its id")
    @ApiOperation(value = "Get consumo given an id", notes = "Find the consumo that match the given id", response = Consumo.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An Consumo from given Id"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Consumo Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Consumo> getConsumoById(@PathVariable final String id);

    @ApiModelProperty(position = 4, required = true, value = "Deletes an consumo given its id")
    @ApiOperation(value = "Delete consumo", notes = "Delete consumo that match the given id", response = Consumo.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 410, message = "The consumo has been deleted"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Consumo Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/delete/{id}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Consumo> deleteConsumoById(@PathVariable final String id);

    @ApiModelProperty(position = 5, required = true, value = "Creates an consumo")
    @ApiOperation(value = "Create consumo", notes = "Creates an consumo given its information", response = Consumo.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "The consumo has been created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Consumo Not created"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Consumo> createConsumo(
        @ApiParam(value = "The consumo object", required = true) @RequestBody(required = true)
            Consumo consumo);

    @ApiModelProperty(position = 6, required = true, value = "Consumos report in Excel")
    @ApiOperation(value = "Consumos report in Excel", notes = "Consumos report in Excel", response = String.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "The report path"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Consumos Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/report", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public void generateReport(HttpServletResponse response) throws IOException;
}
