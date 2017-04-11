package com.biotrends.api.consumo.impl;

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

import com.biotrends.api.consumo.ConsumoController;
import com.biotrends.entities.consumo.Consumo;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.services.consumo.ConsumoService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

/**
 * @author Oscar Malagon
 * @since 23/12/2016.
 */
@RestController
@Api(value = "api-consumo", description = "Consumo MVC EndPoint", produces = APPLICATION_HAL_JSON_VALUE)
@RequestMapping(value = "/api/consumo", produces = APPLICATION_HAL_JSON_VALUE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DefaultConsumoControllerImpl implements ConsumoController {

    private final ConsumoService service;

    @Autowired public DefaultConsumoControllerImpl(ConsumoService service) {
        this.service = service;
    }

    @Override @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<Consumo>> getConsumos() {
        List<Consumo> itemList = service.findAll();

        return new ResponseEntity<>(itemList, OK);
    }

    @Override
    @RequestMapping(value = "/{page}/{size}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Page<Consumo>> getConsumos(@PathVariable int page,
        @PathVariable int size) {
        Page<Consumo> itemPage = service.findAll(page, size);

        return new ResponseEntity<>(itemPage, OK);
    }

    @Override @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Consumo> getConsumoById(@PathVariable final String id) {
        Optional<Consumo> itemById = service.findById(id);
        if (itemById.isPresent()) {

            return new ResponseEntity<>(itemById.get(), OK);
        }

        throw new EntityNotFoundException("Consumo not found by id [" + id + "]");
    }

    @Override
    @RequestMapping(value = "/delete/{id}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Consumo> deleteConsumoById(@PathVariable final String id) {
        Optional<Consumo> itemById = service.delete(id);
        if (itemById.isPresent()) {

            return new ResponseEntity<>(itemById.get(), GONE);
        }

        throw new EntityNotFoundException("Consumo not found by id [" + id + "]");
    }

    @Override
    @RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Consumo> createConsumo(
        @ApiParam(value = "The item object", required = true) @RequestBody(required = true)
            Consumo item) {

        if (item != null) {
            Optional<Consumo> itemGuardado = service.createOrUpdateConsumo(item);
            if (itemGuardado.isPresent()) {
                return new ResponseEntity<Consumo>(itemGuardado.get(), OK);
            }

            throw new EntityNotFoundException("Consumo no guardado");
        }
        return null;
    }

    @Override
    @RequestMapping(value = "/report", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public void generateReport(HttpServletResponse response) throws IOException {
        /*String filePath = service.generateReport();
        File reporte = new File(filePath);
        String type = URLConnection.guessContentTypeFromName(reporte.getName());
        response.setContentType(type);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"%s\"", reporte.getName()));
        response.setContentLength((int)reporte.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(reporte));

        FileCopyUtils.copy(inputStream, response.getOutputStream());*/

        throw new CommonBiotrendsRuntimeException("Not implemented yet");
    }

	@Override
	@RequestMapping(value = "/item/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<List<Consumo>> getConsumoByItemId(@PathVariable final String id) {
		List<Consumo> consumos = service.findByIdItem(id);
		return new ResponseEntity<>(consumos, OK);
	}

}
