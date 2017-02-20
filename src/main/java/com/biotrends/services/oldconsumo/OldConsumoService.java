package com.biotrends.services.oldconsumo;

import java.util.List;

import com.biotrends.entities.oldconsumo.OldConsumoDTO;

/**
 * @author Oscar Malagon
 * @since 27/01/2017.
 */
public interface OldConsumoService {

    List<OldConsumoDTO> listadoDeConsumos();
    
    List<OldConsumoDTO> listadoDeConsumosPorFecha(String fecha);
    
    String generateReport(String fecha);
}
