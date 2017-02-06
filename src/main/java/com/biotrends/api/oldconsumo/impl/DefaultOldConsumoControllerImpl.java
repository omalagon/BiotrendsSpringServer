package com.biotrends.api.oldconsumo.impl;

import com.biotrends.api.oldconsumo.OldConsumoController;
import com.biotrends.entities.oldconsumo.OldConsumoDTO;
import com.biotrends.services.oldconsumo.OldConsumoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Oscar Malagon
 * @since 27/01/2017.
 */
@RestController
@Api(value = "api-old-consumos", description = "Consumos MVC EndPoint", produces = APPLICATION_HAL_JSON_VALUE)
@RequestMapping(value = "/api/oldconsumo", produces = APPLICATION_HAL_JSON_VALUE)
public class DefaultOldConsumoControllerImpl implements OldConsumoController {

    private final OldConsumoService service;

    @Autowired
    public DefaultOldConsumoControllerImpl(OldConsumoService service){
        this.service = service;
    }
    @Override
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<OldConsumoDTO>> getConsumos() {
        return new ResponseEntity<List<OldConsumoDTO>>(service.listadoDeConsumos(), OK);
    }
}
