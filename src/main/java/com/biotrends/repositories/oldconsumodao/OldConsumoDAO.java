package com.biotrends.repositories.oldconsumodao;

import com.biotrends.entities.oldconsumo.OldConsumoDTO;

import java.util.List;

/**
 * @author Oscar Malagon
 * @since 27/01/2017.
 */
public interface OldConsumoDAO {

    List<OldConsumoDTO> getConsumos();
}
