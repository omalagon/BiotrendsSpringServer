package com.biotrends.entities.item;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Oscar Malagon
 * @since 6/01/2017.
 */
@Data
@Embeddable
@SuppressWarnings("squid:S1068")
public class ItemBase implements Serializable{

    private static final long serialVersionUID = -5015815182162586358L;

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

    @Column(name = "ITEM_CCAL")
    @NotNull
    private Boolean cCalidad;

    @Column(name = "ITEM_CESP")
    @NotNull
    private Boolean cEsp;

    @Builder
    public static ItemBase target( String inventario,String descripcion,String presentacion, Double cantidad, Double precio, Boolean cCalidad,Boolean cEsp ){
        ItemBase item = new ItemBase();
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
