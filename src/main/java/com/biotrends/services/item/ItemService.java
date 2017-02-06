package com.biotrends.services.item;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.biotrends.entities.item.Item;

/**
 * @author Oscar Malagon
 * @since 22/12/2016.
 */
public interface ItemService {

    Optional<Item> createOrUpdateItem(Item item);

    Optional<Item> findById(String id);

    Page<Item> findAll(int page, int size);

    List<Item> findAll();

    Optional<Item> delete(String id);

    String generateReport();

}
