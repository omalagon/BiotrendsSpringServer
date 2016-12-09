package com.biotrends.repositories.item;

import com.biotrends.entities.item.Item;
import com.biotrends.repositories.EntityRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Oscar Malagon
 * @since 5/12/2016.
 */
@Repository
public interface ItemRepository extends EntityRepository<Item>{
}
