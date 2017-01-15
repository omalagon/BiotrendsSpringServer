package com.biotrends.services.descargo;

import com.biotrends.entities.consumo.Consumo;

import java.util.List;
import java.util.Optional;

/**
 * @author Oscar Malagon
 * @since 23/12/2016.
 */
public interface DescargoService {

    Optional<Consumo> createOrUpdateItem(Consumo consumo);

    Optional<Consumo> findById(String id);

    List<Consumo> findAll();

    Optional<Consumo> delete(Consumo consumo);
}
