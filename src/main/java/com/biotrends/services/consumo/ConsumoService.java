package com.biotrends.services.consumo;

import com.biotrends.entities.consumo.Consumo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author Oscar Malagon
 * @since 23/12/2016.
 */
public interface ConsumoService {

    Optional<Consumo> createOrUpdateConsumo(Consumo consumo);

    Optional<Consumo> findById(String id);

    Page<Consumo> findAll(int page, int size);

    List<Consumo> findAll();

    Optional<Consumo> delete(String id);
}

