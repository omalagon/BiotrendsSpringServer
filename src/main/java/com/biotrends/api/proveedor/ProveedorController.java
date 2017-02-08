package com.biotrends.api.proveedor;

import com.biotrends.entities.proveedor.Proveedor;
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
 * @since 08/02/2017.
 */
public interface ProveedorController {

    @ApiModelProperty(position = 1, required = true, value = "List of proveedores")
    @ApiOperation(value = "Get proveedores", notes = "Find all proveedores", response = Proveedor.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "All proveedores"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Proveedor Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<Proveedor>> getProveedores();

    @ApiModelProperty(position = 2, required = true, value = "Page of proveedores")
    @ApiOperation(value = "Get proveedores", notes = "Find all proveedores at the page", response = Proveedor.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An Proveedor from given page and size"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Proveedor Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Page<Proveedor>> getProveedores(@PathVariable final int page,
        @PathVariable final int size);

    @ApiModelProperty(position = 3, required = true, value = "Proveedor given its id")
    @ApiOperation(value = "Get proveedor given an id", notes = "Find the proveedor that match the given id", response = Proveedor.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An Proveedor from given Id"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Proveedor Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable final String id);

    @ApiModelProperty(position = 4, required = true, value = "Deletes an proveedor given its id")
    @ApiOperation(value = "Delete proveedor", notes = "Delete proveedor that match the given id", response = Proveedor.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 410, message = "The proveedor has been deleted"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Proveedor Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/delete/{id}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Proveedor> deleteProveedorById(@PathVariable final String id);

    @ApiModelProperty(position = 5, required = true, value = "Creates an proveedor")
    @ApiOperation(value = "Create proveedor", notes = "Creates an proveedor given its information", response = Proveedor.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "The proveedor has been created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Proveedor Not created"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Proveedor> createProveedor(
        @ApiParam(value = "The proveedor object", required = true) @RequestBody(required = true)
            Proveedor proveedor);

    @ApiModelProperty(position = 6, required = true, value = "Proveedores report in Excel")
    @ApiOperation(value = "Proveedores report in Excel", notes = "Proveedores report in Excel", response = String.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "The report path"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Proveedores Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/report", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public void generateReport(HttpServletResponse response) throws IOException;
}
