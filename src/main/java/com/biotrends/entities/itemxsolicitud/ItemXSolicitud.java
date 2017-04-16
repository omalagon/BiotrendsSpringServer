package com.biotrends.entities.itemxsolicitud;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.biotrends.entities.BiotrendsBaseEntity;
import com.biotrends.entities.proveedor.Proveedor;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Oscar Malagon
 * @since 09/02/2017
 */

@Data
@Entity
@Table(name ="BIOT_ITEM_X_SOLICITUD")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ItemXSolicitud extends BiotrendsBaseEntity{
	
	private static final long serialVersionUID = -4440296137105364514L;

	@Column(length= 100, name = "IXS_ITEM")
    @NotNull
	private String idItem;
	
	@Column(length= 100, name = "IXS_CANT_SOL")
    @NotNull
	private Double cantidadSolicitada;

	@Column(length= 100, name = "IXS_CANT_APR", nullable = true)
	private Double cantidadAprobada;
	
	@OneToOne(fetch = FetchType.LAZY)
	@Column(name = "IXS_PROV", nullable = true)
	private Proveedor proveedor;
	
	@Column(name = "IXS_GENERADO", nullable = true)
	private Boolean generado = false;
	
	@Column(name = "IXS_NUM_ORDEN", nullable = true)
	private Long numOrdenAsociado;
	
	@Builder
	public static ItemXSolicitud target(String idItem,
			Double cantidadSolicitada,
			Double cantidadAprobada,
			Proveedor proveedor, 
			Boolean generado,
			Long numOrdenAsociado){
		ItemXSolicitud itemXSolicitud = new ItemXSolicitud();
		itemXSolicitud.setIdItem(idItem);
		itemXSolicitud.setCantidadSolicitada(cantidadSolicitada);
		itemXSolicitud.setCantidadAprobada(cantidadAprobada);
		itemXSolicitud.setProveedor(proveedor);
		itemXSolicitud.setGenerado(generado);
		itemXSolicitud.setNumOrdenAsociado(numOrdenAsociado);
		
		return itemXSolicitud;
	}
	
}
