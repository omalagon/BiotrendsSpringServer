package com.biotrends.api.item.impl;

import com.biotrends.api.item.ItemController;
import com.biotrends.entities.item.Item;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.services.item.ItemService;
import com.biotrends.utils.StringResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.util.List;
import java.util.Optional;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static com.google.common.base.Strings.isNullOrEmpty;
import static org.springframework.http.HttpStatus.GONE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Oscar Malagon
 * @since 23/12/2016.
 */
@RestController
@Api(value = "api-item", description = "Item MVC EndPoint", produces = APPLICATION_HAL_JSON_VALUE)
@RequestMapping(value = "/api/item", produces = APPLICATION_HAL_JSON_VALUE)
public class ItemControllerImpl implements ItemController {

    private final ItemService itemService;

    @Autowired public ItemControllerImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<Item>> getItems() {
        List<Item> itemList = itemService.findAll();

        return new ResponseEntity<>(itemList, OK);
    }

    @Override @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Item> getItemById(@PathVariable final String id) {
        Optional<Item> itemById = itemService.findById(id);
        if (itemById.isPresent()) {

            return new ResponseEntity<>(itemById.get(), OK);
        }

        throw new EntityNotFoundException("Item not found by id [" + id + "]");
    }

    @Override
    @RequestMapping(value = "/delete/{id}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Item> deleteItemById(@PathVariable final String id) {
        Optional<Item> itemById = itemService.delete(id);
        if (itemById.isPresent()) {

            return new ResponseEntity<>(itemById.get(), GONE);
        }

        throw new EntityNotFoundException("Item not found by id [" + id + "]");
    }

    @Override
    @RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Item> createItem(
        @ApiParam(value = "The item object", required = true) @RequestBody(required = true)
            Item item) {

        if (item != null) {
            Optional<Item> itemGuardado = itemService.createOrUpdateItem(item);
            if (itemGuardado.isPresent()) {
                return new ResponseEntity<Item>(itemGuardado.get(), OK);
            }

            throw new EntityNotFoundException("Item no guardado");
        }
        return null;
    }

    @Override
    @RequestMapping(value = "/report", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<StringResponse> generateReport() {
        String filePath = itemService.generateReport();
        if (!isNullOrEmpty(filePath)) {
            StringResponse stringResponse = StringResponse.builder().response(filePath).build();

            return new ResponseEntity<>(stringResponse, OK);
        }

        throw new CommonBiotrendsRuntimeException("Error generando el reporte");
    }


}
