package com.biotrends.repositories.ordencompra;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biotrends.entities.ordencompra.OrdenCompra;
import com.biotrends.repositories.EntityRepository;

/**
 * @author Oscar Malagon
 * @since 5/12/2016.
 */
@Repository
public interface OrdenCompraRepository extends EntityRepository<OrdenCompra>{
	
	@Query("SELECT MAX(ordencompra.numeroOrden) FROM OrdenCompra ordencompra")
	Long getLastOrdenCompra();
	
	@Query("SELECT ordencompra FROM OrdenCompra ordencompra WHERE ordencompra.numeroOrden = :numeroOrden")
	OrdenCompra getOrdenCompraByNumeroOrden(@Param("numeroOrden") final Long numeroOrden);
}
