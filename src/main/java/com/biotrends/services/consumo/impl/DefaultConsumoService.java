package com.biotrends.services.consumo.impl;

import com.biotrends.entities.consumo.Consumo;
import com.biotrends.entities.item.Item;
import com.biotrends.entities.item.ItemBase;
import com.biotrends.entities.usuario.Usuario;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.repositories.consumo.ConsumoRepository;
import com.biotrends.services.consumo.ConsumoService;
import com.biotrends.services.item.ItemService;
import com.biotrends.services.usuario.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Oscar Malagon
 * @since 14/01/2017.
 */
@Service @Slf4j public class DefaultConsumoService implements ConsumoService {

    private static final String OPERATOR_MINUS = "-";
    private static final String OPERATOR_PLUS = "+";
    
	private final ConsumoRepository repository;
    private final UsuarioService usuarioService;
    private final ItemService itemService;

    @Autowired public DefaultConsumoService(ConsumoRepository consumoRepository, UsuarioService usuarioService, ItemService itemService) {
        this.repository = consumoRepository;
        this.usuarioService = usuarioService;
        this.itemService = itemService;
    }

    @Override public Optional<Consumo> createOrUpdateConsumo(Consumo consumo) {
        checkNotNull(consumo.getItem(), "El item del consumo no puede ser nulo");
        checkNotNull(consumo.getUsuario(), "El usuario que realizó el consumo no puede ser nulo");

        Optional<Usuario> usuario = usuarioService.findById(consumo.getUsuario());
        checkArgument(usuario.isPresent(), "El usuario ingresado no existe");

        Optional<Item> item = itemService.findById(consumo.getItem().getId());
        checkArgument(item.isPresent(), "El item ingresado no existe");

        item = updateItemCantidad(consumo, item, OPERATOR_MINUS);
        
        if (consumo.getId() != null && item.isPresent()) {
            Optional<Consumo> consumoEncontrado = findById(consumo.getId());
            if (consumoEncontrado.isPresent()) {
                Consumo updatedConsumo = consumoEncontrado.get();
                updatedConsumo.setArea(consumo.getArea());
                updatedConsumo.setCantidad(consumo.getCantidad());
                updatedConsumo.setFechaDescargo(consumo.getFechaDescargo());
                updatedConsumo.setItem(consumo.getItem());
                updatedConsumo.setUsuario(consumo.getUsuario());

                return Optional.ofNullable(repository.saveAndFlush(updatedConsumo));
            }

            log.error("No fue posible actualizar el consumo");
            throw new EntityNotFoundException();

        }

        return Optional.ofNullable(repository.saveAndFlush(consumo));
    }

    @Override public Optional<Consumo> findById(String id) {
        checkNotNull(id, "El id del consumo no puede ser nulo");

        try {
            Consumo consumo = repository.findOne(id);

            return Optional.ofNullable(consumo);
        } catch (Exception ex) {
            log.error("Error buscando el consumo con id [" + id + "]", ex);
            throw new EntityNotFoundException();
        }
    }

    @Override public Page<Consumo> findAll(int page, int size) {
        checkNotNull(page, "La página no puede ser null");
        checkNotNull(size, "El tamaño de la pagina no puede ser null");
        checkArgument(page >= 0, "La página no puede ser negativa");
        checkArgument(size >= 0, "El tamaño de la página no puede ser negativa");

        try {
            Pageable pageable = new PageRequest(page, size);
            return repository.findAll(pageable);
        } catch (Exception ex) {
            log.error("Error buscando los consumo", ex);
            throw new EntityNotFoundException();
        }
    }

    @Override public List<Consumo> findAll() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            log.error("Error buscando los consumos", ex);
            throw new EntityNotFoundException();
        }
    }

    @Override public Optional<Consumo> delete(String id) {
        Optional<Consumo> consumoById = findById(id);
        
        try {
            if(consumoById.isPresent()){
            	Optional<Item> item = itemService.findById(consumoById.get().getItem().getId());
                checkArgument(item.isPresent(), "El item ingresado no existe");

                item = updateItemCantidad(consumoById.get(), item, OPERATOR_PLUS);
                
                repository.delete(consumoById.get());
            }

            return consumoById;
        } catch (Exception ex) {
            log.error("Error eliminando el consumo con id [" + id + "]", ex);
            throw new CommonBiotrendsRuntimeException("Error eliminando el consumo con id [" + id + "]", ex);
        }
    }
    

	/**
	 * @param consumo
	 * @param item
	 * @return the item with the Cantidad updated
	 */
	private Optional<Item> updateItemCantidad(Consumo consumo, Optional<Item> item, String operator) {
		
		Double cantidad = operator.equalsIgnoreCase(OPERATOR_MINUS) ? consumo.getCantidad() * -1 : consumo.getCantidad();
		
		Item updateItem  = item.get();
        ItemBase updateItmB = updateItem.getItemBase();
        updateItmB.setCantidad(updateItmB.getCantidad() + cantidad);
        
        updateItem.setItemBase(updateItmB);
        item = itemService.createOrUpdateItem(updateItem);
		return item;
	}

}
