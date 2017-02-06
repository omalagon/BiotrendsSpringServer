package com.biotrends.api.solicitud.impl;

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

import com.biotrends.api.solicitud.SolicitudController;
import com.biotrends.entities.solicitud.Solicitud;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.services.solicitud.SolicitudService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

/**
 * @author Oscar Malagon
 * @since 06/02/2017.
 */
@RestController
@Api(value = "api-solicitud", description = "Solicitud MVC EndPoint", produces = APPLICATION_HAL_JSON_VALUE)
@RequestMapping(value = "/api/solicitud", produces = APPLICATION_HAL_JSON_VALUE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DefaultSolicitudControllerImpl implements SolicitudController {

    private final SolicitudService service;

    @Autowired public DefaultSolicitudControllerImpl(SolicitudService service) {
        this.service = service;
    }

    @Override @RequestMapping(value = "/", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<List<Solicitud>> getSolicitudes() {
        List<Solicitud> solicitudList = service.findAll();

        return new ResponseEntity<>(solicitudList, OK);
    }

    @Override
    @RequestMapping(value = "/{page}/{size}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Page<Solicitud>> getSolicitudes(@PathVariable int page,
        @PathVariable int size) {
        Page<Solicitud> itemPage = service.findAll(page, size);

        return new ResponseEntity<>(itemPage, OK);
    }

    @Override @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Solicitud> getSolicitudById(@PathVariable final String id) {
        Optional<Solicitud> itemById = service.findById(id);
        if (itemById.isPresent()) {

            return new ResponseEntity<>(itemById.get(), OK);
        }

        throw new EntityNotFoundException("Solicitud not found by id [" + id + "]");
    }

    @Override
    @RequestMapping(value = "/delete/{id}", method = DELETE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Solicitud> deleteSolicitudById(@PathVariable final String id) {
        Optional<Solicitud> itemById = service.delete(id);
        if (itemById.isPresent()) {

            return new ResponseEntity<>(itemById.get(), GONE);
        }

        throw new EntityNotFoundException("Solicitud not found by id [" + id + "]");
    }

    @Override
    @RequestMapping(value = "/create", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_HAL_JSON_VALUE)
    public ResponseEntity<Solicitud> createSolicitud(
        @ApiParam(value = "The item object", required = true) @RequestBody(required = true)
            Solicitud item) {

        if (item != null) {
            Optional<Solicitud> itemGuardado = service.createOrUpdateSolicitud(item);
            if (itemGuardado.isPresent()) {
                return new ResponseEntity<>(itemGuardado.get(), OK);
            }

            throw new EntityNotFoundException("Solicitud no guardado");
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

}
