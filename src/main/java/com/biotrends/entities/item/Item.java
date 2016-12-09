package com.biotrends.entities.item;


import com.biotrends.entities.BiotrendsBaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Oscar Malagon
 * @since 4/12/2016.
 */

@Data
@Entity
@Table(name = "BIO_ITEM")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("squid:S1068")
public class Item extends BiotrendsBaseEntity{

    @Column(length= 100, name = "ITEM_INV")
    @NotNull
    private String inventario;

    @Column(length= 100, name = "ITEM_DESC")
    @NotNull
    private String descripcion;

    @Column(length= 100, name = "ITEM_PRES")
    @NotNull
    private String presentacion;

    @Column(name = "ITEM_CANT")
    @NotNull
    private Double cantidad;

    @Column(name = "ITEM_PREC")
    @NotNull
    private Double precio;

    @Column(length= 100, name = "ITEM_CCAL")
    @NotNull
    private String cCalidad;

    @Column(length= 100, name = "ITEM_CESP")
    @NotNull
    private String cEsp;

    @Builder
    public Item target(String id, String inventario,String descripcion,String presentacion, Double cantidad, Double precio, String cCalidad,String cEsp ){
        Item item = new Item();
        item.setId(id);
        item.setInventario(inventario);
        item.setDescripcion(descripcion);
        item.setPresentacion(presentacion);
        item.setCantidad(cantidad);
        item.setPrecio(precio);
        item.setCCalidad(cCalidad);
        item.setCEsp(cEsp);

        return item;
    }

}
