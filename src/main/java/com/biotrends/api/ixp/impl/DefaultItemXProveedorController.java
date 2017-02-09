package com.biotrends.api.ixp.impl;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static org.springframework.http.HttpStatus.GONE;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biotrends.api.ixp.ItemXProveedorController;
import com.biotrends.entities.itemxproveedor.ItemXProveedor;
import com.biotrends.services.itemxproveedor.ItemxProveedorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "api-itemxproveedor", description = "ItemxProveedor MVC EndPoint", produces = APPLICATION_HAL_JSON_VALUE)
@RequestMapping(value = "/api/itemxproveedor", produces = APPLICATION_HAL_JSON_VALUE)
public class DefaultItemXProveedorController implements ItemXProveedorController{
	
	private final ItemxProveedorService service;

	@Autowired
	public DefaultItemXProveedorController(ItemxProveedorService service) {
		this.service = service;
	}
	
	@Override
	@RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<List<ItemXProveedor>> getItemXProveedor() {
		List<ItemXProveedor> ixpList = service.findAll();
		return new ResponseEntity<>(ixpList, OK);
	}

	@Override
	@RequestMapping(value = "/{page}/{size}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<Page<ItemXProveedor>> getItemXProveedor(
			@PathVariable final int page, 
			@PathVariable final int size) {
		Page<ItemXProveedor> ixpPage = service.findAll(page, size);
        return new ResponseEntity<>(ixpPage, OK);
	}

	@Override
	@RequestMapping(value = "/proveedor/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<Set<ItemXProveedor>> getItemXProveedorByProveedorId(@PathVariable final String id) {
		Set<ItemXProveedor> ixpById = service.findByIdProveedor(id);
        if(!ixpById.isEmpty()){
        	
        	return new ResponseEntity<>(ixpById, OK);
        }
        
        return new ResponseEntity<>(NO_CONTENT);
        
	}

	@Override
	@RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<ItemXProveedor> getItemXProveedorById(@PathVariable final String id) {
		Optional<ItemXProveedor> ixpById = service.findById(id);
        if (ixpById.isPresent()) {

            return new ResponseEntity<>(ixpById.get(), OK);
        }

        throw new EntityNotFoundException("IXP not found by id [" + id + "]");
	}

	@Override
	@RequestMapping(value = "/delete/{id}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<ItemXProveedor> deleteItemXProveedorById(@PathVariable final String id) {
		Optional<ItemXProveedor> ixpById = service.delete(id);
        if (ixpById.isPresent()) {

            return new ResponseEntity<>(ixpById.get(), GONE);
        }

        throw new EntityNotFoundException("IXP not found by id [" + id + "]");
	}

	@Override
	@RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<ItemXProveedor> createItemXProveedor(
			@ApiParam(value = "The itemxproveedor object", required = true) @RequestBody(required = true)
			ItemXProveedor itemxproveedor) {
		if(itemxproveedor!= null){
			Optional<ItemXProveedor> ixpGuardado = service.createOrUpdateItemxProveedor(itemxproveedor);
			if(ixpGuardado.isPresent()){
				return new ResponseEntity<ItemXProveedor>(ixpGuardado.get(), OK);
			}
			
			throw new EntityNotFoundException("IXP no guardado");
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/ids/{idItem}/{idProveedor}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<ItemXProveedor> getItemXProveedorByProveedorIdAndItemId(
			@PathVariable final String idItem, 
			@PathVariable final String idProveedor) {
		Optional<ItemXProveedor> ixpById = service.findByIdItemAndIdProveedor(idItem, idProveedor);
        if (ixpById.isPresent()) {

            return new ResponseEntity<>(ixpById.get(), OK);
        }

        throw new EntityNotFoundException("IXP not found by idItem and idProveedor [" + idItem + "," +  idProveedor + "]");
	}

}
