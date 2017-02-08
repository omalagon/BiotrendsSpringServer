package com.biotrends.repositories.itemxproveedor;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biotrends.entities.itemxproveedor.ItemXProveedor;
import com.biotrends.repositories.EntityRepository;

/**
 * @author Oscar Malagon
 * @since 5/12/2016.
 */
@Repository
public interface ItemxProveedorRepository extends EntityRepository<ItemXProveedor>{

	@Query("SELECT ixp from ItemXProveedor ixp where ixp.item.id = :idItem AND ixp.proveedor.id = :idProveedor")
	ItemXProveedor findByItsIds(@Param("idItem") final String idItem,@Param("idProveedor") final String idProveedor);
	
	@Query("SELECT ixp from ItemXProveedor ixp where ixp.proveedor.id = :idProveedor")
	Set<ItemXProveedor> findByIdProveedor(@Param("idProveedor") final String idProveedor);
	
}
