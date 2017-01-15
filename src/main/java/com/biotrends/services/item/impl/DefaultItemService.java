package com.biotrends.services.item.impl;

import com.biotrends.entities.item.Item;
import com.biotrends.entities.item.ItemBase;
import com.biotrends.repositories.item.ItemRepository;
import com.biotrends.services.item.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Oscar Malagon
 * @since 22/12/2016.
 */
@Slf4j @Service public class DefaultItemService implements ItemService {

    private final ItemRepository itemRepository;

    public DefaultItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override public Optional<Item> createOrUpdateItem(Item item) {
        if(item.getId() != null){
            Optional<Item> itemEncontrado = findById(item.getId());
            if(itemEncontrado.isPresent()){
                //updates
            }else{
                return Optional.ofNullable(itemRepository.saveAndFlush(item));
            }
        }

        return null;
    }

    @Override public Optional<Item> findById(String id) {
        checkNotNull(id, "El id del ítem no puede ser nulo");

        try {
            Item item = itemRepository.findOne(id);

            return Optional.ofNullable(item);
        } catch (Exception ex) {
            log.error("Error buscando el item con id [" + id + "]", ex);
            throw new RuntimeException("Error buscando el item con id [" + id + "]", ex);
        }
    }

    @Override public List<Item> findAll() {
        try {
            return itemRepository.findAll();
        } catch (Exception ex) {
            log.error("Error buscando los items", ex);
            throw new RuntimeException("Error buscando los items", ex);
        }
    }

    @Override public Optional<Item> delete(String id) {
        Optional<Item> itemById = findById(id);
        try {
            itemRepository.delete(itemById.get());

            return itemById;
        } catch (Exception ex) {
            log.error("Error eliminando el item con id [" + id + "]", ex);
            throw new RuntimeException("Error buscando el item con id [" + id + "]", ex);
        }
    }

    private Sort orderByInventarioAndDescripcion() {
        return new Sort(Sort.Direction.ASC, "itemBase.inventario", "itemBase.descripcion");
    }
}
