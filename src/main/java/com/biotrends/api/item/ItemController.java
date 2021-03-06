package com.biotrends.api.item;

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

import com.biotrends.entities.item.Item;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Oscar Malagon
 * @since 10/01/2017.
 */
public interface ItemController {

    @ApiModelProperty(position = 1, required = true, value = "List of items")
    @ApiOperation(value = "Get items", notes = "Find all items", response = Item.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An Item from given Id"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Item Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<Item>> getItems();

    @ApiModelProperty(position = 2, required = true, value = "Page of items")
    @ApiOperation(value = "Get items", notes = "Find all items at the page", response = Item.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An Item from given Id"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Item Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Page<Item>> getItems(@PathVariable final int page,
                                               @PathVariable final int size);

    @ApiModelProperty(position = 3, required = true, value = "Item given its id")
    @ApiOperation(value = "Get item given an id", notes = "Find the item that match the given id", response = Item.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "An Item from given Id"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Item Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Item> getItemById(@PathVariable final String id);

    @ApiModelProperty(position = 4, required = true, value = "Deletes an item given its id")
    @ApiOperation(value = "Delete item", notes = "Delete item that match the given id", response = Item.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 410, message = "The item has been deleted"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Item Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/delete/{id}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Item> deleteItemById(@PathVariable final String id);

    @ApiModelProperty(position = 5, required = true, value = "Creates an item")
    @ApiOperation(value = "Create item", notes = "Creates an item given its information", response = Item.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "The item has been created"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Item Not created"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Item> createItem(
        @ApiParam(value = "The item object", required = true) @RequestBody(required = true)
            Item item);

    @ApiModelProperty(position = 6, required = true, value = "Items report in Excel")
    @ApiOperation(value = "Items report in Excel", notes = "Items report in Excel", response = String.class, produces = APPLICATION_HAL_JSON_VALUE)
    @ApiResponses({@ApiResponse(code = 200, message = "The report path"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized Request"),
        @ApiResponse(code = 404, message = "Items Not Found"),
        @ApiResponse(code = 500, message = "Unexpected Internal Server Error")})
    @RequestMapping(value = "/report", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public void generateReport(HttpServletResponse response) throws IOException;
}
