package com.biotrends.api.proveedor.impl;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static org.springframework.http.HttpStatus.GONE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biotrends.api.proveedor.ProveedorController;
import com.biotrends.entities.proveedor.Proveedor;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.services.proveedor.ProveedorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "api-proveedor", description = "Proveedor MVC EndPoint", produces = APPLICATION_HAL_JSON_VALUE)
@RequestMapping(value = "/api/proveedor", produces = APPLICATION_HAL_JSON_VALUE)
public class DefaultProveedorController implements ProveedorController{

	private final ProveedorService service;
	
	@Autowired
	public DefaultProveedorController(ProveedorService service) {
		this.service = service;
	}
	
	@Override
	@RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<List<Proveedor>> getProveedores() {
		List<Proveedor> proveedorList = service.findAll();
		return new ResponseEntity<>(proveedorList, OK);
	}

	@Override
	@RequestMapping(value = "/{page}/{size}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<Page<Proveedor>> getProveedores(@PathVariable final int page, @PathVariable final int size) {
		Page<Proveedor> proveedorPage = service.findAll(page, size);

        return new ResponseEntity<>(proveedorPage, OK);
        
	}

	@Override
	@RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<Proveedor> getProveedorById(@PathVariable final String id) {
		Optional<Proveedor> provById = service.findById(id);
        if (provById.isPresent()) {

            return new ResponseEntity<>(provById.get(), OK);
        }

        throw new EntityNotFoundException("Proveedor not found by id [" + id + "]");
	}

	@Override
	@RequestMapping(value = "/delete/{id}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<Proveedor> deleteProveedorById(@PathVariable String id) {
		Optional<Proveedor> provById = service.delete(id);
        if (provById.isPresent()) {

            return new ResponseEntity<>(provById.get(), GONE);
        }

        throw new EntityNotFoundException("Proveedor not found by id [" + id + "]");
	}

	@Override
	@RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<Proveedor> createProveedor(
			@ApiParam(value = "The proveedor object", required = true) @RequestBody(required = true)
			Proveedor proveedor) {
		if (proveedor != null) {
            Optional<Proveedor> provGuardado = service.createOrUpdateProveedor(proveedor);
            if (provGuardado.isPresent()) {
                return new ResponseEntity<>(provGuardado.get(), OK);
            }

            throw new EntityNotFoundException("Proveedor no guardado");
        }
        return null;
	}

	@Override
	@RequestMapping(value = "/report", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
	public void generateReport(HttpServletResponse response) throws IOException {
		throw new CommonBiotrendsRuntimeException("Not implemented yet");
	}

}
