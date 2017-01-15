package com.biotrends.api.item;

import com.biotrends.entities.item.Item;
import com.biotrends.services.item.ItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;

/**
 * @author Oscar Malagon
 * @since 23/12/2016.
 */
@RestController
@Api(value = "api-item", description = "Item MVC EndPoint", produces = APPLICATION_HAL_JSON_VALUE )
@RequestMapping(value = "/api/item", produces = APPLICATION_HAL_JSON_VALUE)
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<Item>> getItemById(){
        List<Item> itemList = itemService.findAll();

        return new ResponseEntity<List<Item>>(itemList, OK);
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Item> getItemById(
        @PathVariable final String id
    ){
        Optional<Item> itemById = itemService.findById(id);
        if(itemById.isPresent()){

            return new ResponseEntity<Item>(itemById.get(), OK);
        }

        throw new EntityNotFoundException("Item not found by id [" + id + "]");
    }

    @RequestMapping(value = "/delete/{id}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Item> deleteItemById(
        @PathVariable final String id
    ){
        System.out.println(id);
        Optional<Item> itemById = itemService.delete(id);
        if(itemById.isPresent()){

            return new ResponseEntity<Item>(itemById.get(), OK);
        }

        throw new EntityNotFoundException("Item not found by id [" + id + "]");
    }


}
