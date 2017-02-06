package com.biotrends.entities.consumo;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.biotrends.entities.BiotrendsBaseEntity;
import com.biotrends.entities.item.Item;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Oscar Malagon
 * @since 4/12/2016.
 */

@Data
@Entity
@Table(name = "BIO_CONSUMO")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("squid:S1068")
public class Consumo extends BiotrendsBaseEntity{

    private static final long serialVersionUID = -5138569755832797011L;

    @Column(length= 100, name = "CONS_FECHA")
    @NotNull
    private Date fechaDescargo;

    @Column(length= 100, name = "CONS_AREA")
    @NotNull
    private String area;

    @Column(name = "CONS_CANT")
    @NotNull
    private Double cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONS_ID_ITEM",foreignKey = @ForeignKey(name = "FK_CONS_ID_ITEM"))
    private Item item;

    @Column(name = "CONS_ID_USU")
    @NotNull
    private String usuario;

    @Builder
    public static Consumo target(String id, Date fechaDescargo, String area,Double cantidad, Item item, String usuario){
        Consumo consumo = new Consumo();
        consumo.setId(id);
        consumo.setFechaDescargo(fechaDescargo);
        consumo.setArea(area);
        consumo.setCantidad(cantidad);
        consumo.setItem(item);
        consumo.setUsuario(usuario);

        return consumo;
    }

}
