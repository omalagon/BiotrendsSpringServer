package com.biotrends.entities.item;


import com.biotrends.entities.BiotrendsBaseEntity;
import com.biotrends.entities.itemxproveedor.ItemXProveedor;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    private static final long serialVersionUID = -5753438717953215092L;

    @Embedded
    @Column(nullable = false)
    private ItemBase itemBase;

    @Builder
    public static Item target(String id, ItemBase itemBase){
        Item item = new Item();
        item.setId(id);
        item.setItemBase(itemBase);

        return item;
    }

}
