package com.biotrends.services.list.laboratorio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biotrends.entities.list.Laboratorio;
import com.biotrends.repositories.list.laboratorio.LaboratorioRespository;
import com.biotrends.services.list.laboratorio.LaboratorioService;

/**
 * @author Oscar Malagon
 * @since 17/04/2017
 */
@Service
public class DefaultLaboratorioService implements LaboratorioService {
	
	private final LaboratorioRespository repository;
	
	@Autowired
	public DefaultLaboratorioService(LaboratorioRespository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Laboratorio> findAll() {
		return repository.findAll();
	}

}
