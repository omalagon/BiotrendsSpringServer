package com.biotrends.entities.itemxproveedor;

import com.biotrends.entities.item.Item;
import com.biotrends.entities.proveedor.Proveedor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Oscar Malagon
 * @since 6/01/2017.
 */
@Data
@Entity
@Table(name = "BIO_ITEM_X_PROVEEDOR")
@ToString(callSuper = true)
public class ItemXProveedor implements Serializable{

    private static final long serialVersionUID = 7917309349094863947L;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ITX_ITEM_ID", nullable = false,
        foreignKey = @ForeignKey(name = "FK_IXP_ITEM_ID"))
    private Item item;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ITX_PROV_ID", nullable = false,
        foreignKey = @ForeignKey(name = "FK_IXP_PROV_ID"))
    private Proveedor proveedor;

    @Column(name = "ITX_PRECIO", nullable = false, precision = 3)
    private Double precio;

    @Builder
    public static ItemXProveedor target(Item item, Proveedor proveedor, Double precio){
        ItemXProveedor itemXProveedor = new ItemXProveedor();
        itemXProveedor.setItem(item);
        itemXProveedor.setProveedor(proveedor);
        itemXProveedor.setPrecio(precio);

        return itemXProveedor;

    }

}
