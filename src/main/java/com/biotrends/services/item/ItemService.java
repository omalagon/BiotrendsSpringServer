package com.biotrends.services;

import com.biotrends.entities.item.Item;

import java.util.List;
import java.util.Optional;

/**
 * @author Oscar Malagon
 * @since 22/12/2016.
 */
public interface ItemService {

    Optional<Item> findById(String id);

    List<Item> findAll();
}
