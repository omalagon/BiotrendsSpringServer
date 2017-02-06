package com.biotrends.repositories.item;

import org.springframework.stereotype.Repository;

import com.biotrends.entities.item.Item;
import com.biotrends.repositories.EntityRepository;

/**
 * @author Oscar Malagon
 * @since 5/12/2016.
 */
@Repository
public interface ItemRepository extends EntityRepository<Item>{
}
