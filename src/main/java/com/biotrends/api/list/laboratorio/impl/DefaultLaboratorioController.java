package com.biotrends.api.list.laboratorio.impl;

import static com.biotrends.api.RestConstants.APPLICATION_HAL_JSON_VALUE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biotrends.api.list.laboratorio.LaboratorioController;
import com.biotrends.assemblers.list.laboratorio.LaboratorioResource;
import com.biotrends.assemblers.list.laboratorio.LaboratorioResourceAssembler;
import com.biotrends.entities.list.Laboratorio;
import com.biotrends.services.list.laboratorio.LaboratorioService;

import io.swagger.annotations.Api;

/**
 * @author Oscar Malagon
 * @since 17/04/2017
 */
@RestController
@Api(value = "api-lists", description = "Lists MVC EndPoint", produces = APPLICATION_HAL_JSON_VALUE)
@RequestMapping(value = "/api/lists", produces = APPLICATION_HAL_JSON_VALUE)
public class DefaultLaboratorioController implements LaboratorioController {

	private final LaboratorioService service;
	private final LaboratorioResourceAssembler assembler;
	
	@Autowired
	public DefaultLaboratorioController(LaboratorioService service, LaboratorioResourceAssembler assembler) {
		this.service = service;
		this.assembler = assembler;
	}
	
	@Override
    @RequestMapping(value = "/laboratorio", method = GET, produces = APPLICATION_HAL_JSON_VALUE)
	public ResponseEntity<List<LaboratorioResource>> getLaboratorio() {
		List<Laboratorio> labs = service.findAll();
		List<LaboratorioResource> resources = new ArrayList<>();
		for (Laboratorio laboratorio : labs) {
			resources.add(assembler.toResource(laboratorio));
		}
		return new ResponseEntity<>(resources, OK);
	}

}
