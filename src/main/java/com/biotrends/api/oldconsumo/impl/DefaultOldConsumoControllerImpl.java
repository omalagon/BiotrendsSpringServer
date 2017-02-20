package com.biotrends.api.oldconsumo.impl;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biotrends.api.oldconsumo.OldConsumoController;
import com.biotrends.entities.oldconsumo.OldConsumoDTO;
import com.biotrends.services.oldconsumo.OldConsumoService;

import io.swagger.annotations.Api;

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
        return new ResponseEntity<>(service.listadoDeConsumos(), OK);
    }
    
	@Override
	@RequestMapping(value = "/report/{dia}/{mes}/{anio}", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
	public void generateReport(HttpServletResponse response, @PathVariable final int dia, @PathVariable final int mes, @PathVariable final int anio) throws IOException {
		
		String fecha = anio + "-" + mes + "-" + dia;
		String filePath = service.generateReport(fecha);
        File reporte = new File(filePath);
        String type = URLConnection.guessContentTypeFromName(reporte.getName());
        response.setContentType(type);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"".concat(reporte.getName()).concat("\"")));
        response.setContentLength((int)reporte.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(reporte));

        FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
}
