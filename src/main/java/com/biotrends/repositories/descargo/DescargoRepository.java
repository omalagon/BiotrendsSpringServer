package com.biotrends.repositories.descargo;

import com.biotrends.entities.descargo.Descargo;
import com.biotrends.repositories.EntityRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Oscar Malagon
 * @since 5/12/2016.
 */
@Repository
public interface DescargoRepository extends EntityRepository<Descargo>{
}
