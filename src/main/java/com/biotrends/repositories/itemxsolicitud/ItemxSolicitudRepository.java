package com.biotrends.repositories.itemxsolicitud;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biotrends.entities.itemxsolicitud.ItemXSolicitud;
import com.biotrends.entities.proveedor.Proveedor;
import com.biotrends.repositories.EntityRepository;

/**
 * @author Oscar Malagon
 * @since 09/02/2017.
 */
@Repository
public interface ItemxSolicitudRepository extends EntityRepository<ItemXSolicitud>{
	
	@Query("SELECT distinct itemxsolicitud.proveedor FROM ItemXSolicitud itemxsolicitud where itemxsolicitud.generado = :generado")
	Proveedor getProveedoresConSolicitudes(@Param("generado") final boolean generado);
	
	@Query("SELECT itemxsolicitud FROM ItemXSolicitud itemxsolicitud WHERE itemxsolicitud.proveedor.id = :idProveedor AND itemxsolicitud.generado = :generado")
	List<ItemXSolicitud> getItemsSolicitadosXProveedor(@Param("idProveedor") final String idProveedor, @Param("generado") final boolean generado);
	
	@Query("SELECT itemxsolicitud FROM ItemXSolicitud itemxsolicitud WHERE itemxsolicitud.numOrdenAsociado = :numeroOrden")
	List<ItemXSolicitud> getItemsByNumeroOrden(@Param("numeroOrden") final Long numeroOrden);
}
