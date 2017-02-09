package com.biotrends.entities.itemxsolicitud;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.biotrends.entities.BiotrendsBaseEntity;

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
@SuppressWarnings("squid:S1068")
public class ItemXSolicitud extends BiotrendsBaseEntity{
	
	private static final long serialVersionUID = -4440296137105364514L;

	@Column(length= 100, name = "IXS_ITEM")
    @NotNull
	private String idItem;
	
	@Column(length= 100, name = "IXS_CANT_SOL")
    @NotNull
	private Double cantidadSolicitada;

	@Column(length= 100, name = "IXS_CANT_APR")
    @NotNull
	private Double cantidadAprobada;
	
}
