package com.biotrends.repositories.consumo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biotrends.entities.consumo.Consumo;
import com.biotrends.repositories.EntityRepository;

/**
 * @author Oscar Malagon
 * @since 5/12/2016.
 */
@Repository
public interface ConsumoRepository extends EntityRepository<Consumo>{
	
	@Query("SELECT consumo FROM Consumo consumo where consumo.item.id = :idItem order by convert(consumo.fechaDescargo, date) DESC")
	List<Consumo> getUltimoConsumoById(@Param("idItem") final String idItem);
	
	@Query("SELECT consumo from Consumo consumo where consumo.item.id = :idItem")
	List<Consumo> findConsumoByItemId(@Param("idItem")final String idItem);
}
