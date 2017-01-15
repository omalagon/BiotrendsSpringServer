package com.biotrends.services.consumo.impl;

import com.biotrends.entities.consumo.Consumo;
import com.biotrends.repositories.consumo.ConsumoRepository;
import com.biotrends.services.consumo.ConsumoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Oscar Malagon
 * @since 14/01/2017.
 */
@Service @Slf4j public class DefaultConsumoService implements ConsumoService {

    private final ConsumoRepository consumoRepository;

    @Autowired public DefaultConsumoService(ConsumoRepository consumoRepository) {
        this.consumoRepository = consumoRepository;
    }

    @Override public Optional<Consumo> createOrUpdateItem(Consumo consumo) {
        checkNotNull(consumo.getItem(), "El item del consumo no puede ser nulo");
        checkNotNull(consumo.getUsuario(), "El usuario que realizó el consumo no puede ser nulo");

        if (consumo.getId() != null) {
            Optional<Consumo> consumoEncontrado = findById(consumo.getId());
            if (consumoEncontrado.isPresent()) {
                //updates
                return Optional.empty();
            }

            log.error("No fue posible actualizar el consumo");
            throw new EntityNotFoundException();

        }

        return Optional.ofNullable(consumoRepository.saveAndFlush(consumo));
    }

    @Override public Optional<Consumo> findById(String id) {
        checkNotNull(id, "El id del consumo no puede ser nulo");

        try {
            Consumo consumo = consumoRepository.findOne(id);

            return Optional.ofNullable(consumo);
        } catch (Exception ex) {
            log.error("Error buscando el consumo con id [" + id + "], ex");
            throw new EntityNotFoundException();
        }
    }

    @Override public List<Consumo> findAll() {
        try {
            return consumoRepository.findAll();
        } catch (Exception ex) {
            log.error("Error buscando los consumos", ex);
            throw new EntityNotFoundException();
        }
    }

    @Override public Optional<Consumo> delete(String id) {
        Optional<Consumo> consumoById = findById(id);
        try {
            consumoRepository.delete(consumoById.get());

            return consumoById;
        } catch (Exception ex) {
            log.error("Error eliminando el consumo con id [" + id + "]", ex);
            throw new RuntimeException("Error eliminando el consumo con id [" + id + "]", ex);
        }
    }

}
