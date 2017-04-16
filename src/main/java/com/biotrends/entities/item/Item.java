package com.biotrends.entities.item;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.biotrends.entities.BiotrendsBaseEntity;

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
@Table(name = "BIO_ITEM")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
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
