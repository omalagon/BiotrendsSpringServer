package com.biotrends.repositories.proveedor;

import com.biotrends.entities.proveedor.Proveedor;
import com.biotrends.repositories.EntityRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Oscar Malagon
 * @since 5/12/2016.
 */
@Repository
public interface ProveedorRepository extends EntityRepository<Proveedor>{
}
