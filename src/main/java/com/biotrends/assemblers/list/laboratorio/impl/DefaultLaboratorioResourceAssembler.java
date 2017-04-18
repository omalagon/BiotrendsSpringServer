package com.biotrends.assemblers.list.laboratorio.impl;

import org.springframework.stereotype.Service;

import com.biotrends.assemblers.list.laboratorio.LaboratorioResource;
import com.biotrends.assemblers.list.laboratorio.LaboratorioResourceAssembler;
import com.biotrends.entities.list.Laboratorio;

/**
 * @author Oscar Malagon
 * @since 17/04/2017
 */
@Service
public class DefaultLaboratorioResourceAssembler implements LaboratorioResourceAssembler {

	@Override
	public LaboratorioResource toResource(Laboratorio entity) {
		if(entity != null){
			return LaboratorioResource.builder()
					.id(entity.getId())
					.descripcion(entity.getDescripcion())
					.build();
		}
		return null;
	}

	@Override
	public Laboratorio fromResource(LaboratorioResource resource) {
		if(resource != null){
			return Laboratorio.builder()
					.id(resource.getIdentifier())
					.descripcion(resource.getDescripcion())
					.build();
		}
		return null;
	}

}
