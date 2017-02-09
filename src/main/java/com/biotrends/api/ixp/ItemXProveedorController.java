package com.biotrends.api.ixp;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biotrends.entities.itemxproveedor.ItemXProveedor;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Oscar Malagon
 * @since 08/02/2017.
 */
public interface ItemXProveedorController {

    @ApiModelProperty(position = 1, required = true, value = "List of itemsXProveedor")
    @ApiOperation(value = "Get itemsXProveedor", notes = "Find all itemsXProveedor", response = ItemXProveedor.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "All itemsXProveedor"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "ItemXProveedor Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<ItemXProveedor>> getItemXProveedor();

    @ApiModelProperty(position = 2, required = true, value = "Page of itemsXProveedor")
    @ApiOperation(value = "Get itemsXProveedor", notes = "Find all itemsXProveedor at the page", response = ItemXProveedor.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An ItemXProveedor from given page and size"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "ItemXProveedor Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Page<ItemXProveedor>> getItemXProveedor(@PathVariable final int page,
        @PathVariable final int size);

    @ApiModelProperty(position = 3, required = true, value = "Get itemxproveedor given the proveedor id")
    @ApiOperation(value = "Get itemxproveedor given the proveedor id", notes = "Get itemxproveedor given the proveedor id", response = ItemXProveedor.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An ItemXProveedor from given proveedor Id"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "ItemXProveedor Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/proveedor/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Set<ItemXProveedor>> getItemXProveedorByProveedorId(@PathVariable final String id);
    
    @ApiModelProperty(position = 4, required = true, value = "Get itemxproveedor given the id")
    @ApiOperation(value = "Get itemxproveedor given the id", notes = "Get itemxproveedor given the id", response = ItemXProveedor.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An ItemXProveedor from given Id"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "ItemXProveedor Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<ItemXProveedor> getItemXProveedorById(@PathVariable final String id);

    @ApiModelProperty(position = 5, required = true, value = "Deletes a itemxproveedor given its id")
    @ApiOperation(value = "Delete itemxproveedor", notes = "Delete itemxproveedor that match the given id", response = ItemXProveedor.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 410, message = "The proveedor has been deleted"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "ItemXProveedor Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/delete/{id}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<ItemXProveedor> deleteItemXProveedorById(@PathVariable final String id);

    @ApiModelProperty(position = 6, required = true, value = "Creates an itemxproveedor")
    @ApiOperation(value = "Create itemxproveedor", notes = "Creates an itemxproveedor given its information", response = ItemXProveedor.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "The itemxproveedor has been created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "ItemXProveedor Not created"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<ItemXProveedor> createItemXProveedor(
        @ApiParam(value = "The itemxproveedor object", required = true) @RequestBody(required = true)
            ItemXProveedor itemxproveedor);

    @ApiModelProperty(position = 7, required = true, value = "Get itemxproveedor given the proveedor id")
    @ApiOperation(value = "Get itemxproveedor given the proveedor id", notes = "Get itemxproveedor given the proveedor id", response = ItemXProveedor.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An ItemXProveedor from given proveedor Id"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "ItemXProveedor Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/{idProveedor}/{idItem}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<ItemXProveedor> getItemXProveedorByProveedorIdAndItemId(@PathVariable final String idProveedor, @PathVariable final String idItem);
}
