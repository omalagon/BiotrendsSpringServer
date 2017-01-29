package com.biotrends.services.oldconsumo.impl;

import com.biotrends.entities.oldconsumo.OldConsumoDTO;
import com.biotrends.repositories.oldconsumodao.OldConsumoDAO;
import com.biotrends.services.oldconsumo.OldConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oscar Malagon
 * @since 27/01/2017.
 */
@Service
public class DefaultOldConsumoService implements OldConsumoService {

    private final OldConsumoDAO oldConsumoDAO;

    @Autowired
    public DefaultOldConsumoService(OldConsumoDAO oldConsumoDAO){
        this.oldConsumoDAO = oldConsumoDAO;
    }

    @Override
    public List<OldConsumoDTO> listadoDeConsumos() {
        return oldConsumoDAO.getConsumos();
    }
}
