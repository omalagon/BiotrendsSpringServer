package com.biotrends.entities.itemxproveedor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.biotrends.entities.BiotrendsBaseEntity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Oscar Malagon
 * @since 6/01/2017.
 */
@Data
@Entity
@Table(name = "BIO_ITEM_X_PROVEEDOR")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ItemXProveedor extends BiotrendsBaseEntity{

    private static final long serialVersionUID = 7917309349094863947L;

    @Column(name = "ITX_ITEM_ID", nullable = false)
    private String item;

    @Column(name = "ITX_PROV_ID", nullable = false)
    private String proveedor;

    @Column(name = "ITX_PRECIO", nullable = false, precision = 3)
    private Double precio;

    @Builder
    public static ItemXProveedor target(String item, String proveedor, Double precio){
        ItemXProveedor itemXProveedor = new ItemXProveedor();
        itemXProveedor.setItem(item);
        itemXProveedor.setProveedor(proveedor);
        itemXProveedor.setPrecio(precio);

        return itemXProveedor;

    }

}
