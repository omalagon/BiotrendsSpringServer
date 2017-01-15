package com.biotrends.services.item;

import com.biotrends.entities.item.Item;

import java.util.List;
import java.util.Optional;

/**
 * @author Oscar Malagon
 * @since 22/12/2016.
 */
public interface ItemService {

    Optional<Item> createOrUpdateItem(Item item);

    Optional<Item> findById(String id);

    List<Item> findAll();

    Optional<Item> delete(String id);

}
