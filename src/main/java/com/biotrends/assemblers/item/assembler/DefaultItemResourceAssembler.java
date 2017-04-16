package com.biotrends.assemblers.item.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biotrends.assemblers.item.ItemResource;
import com.biotrends.assemblers.item.ItemResourceAssembler;
import com.biotrends.assemblers.itembase.ItemBaseAssembler;
import com.biotrends.assemblers.itembase.ItemBaseResource;
import com.biotrends.entities.item.Item;
import com.biotrends.entities.item.ItemBase;

/**
 * @author Oscar Malagon
 * @since 15/04/2017
 */
@Service
public class DefaultItemResourceAssembler implements ItemResourceAssembler {

	private final ItemBaseAssembler<ItemBase, ItemBaseResource> itemBaseAssembler;
	
	@Autowired
	public DefaultItemResourceAssembler(ItemBaseAssembler<ItemBase, ItemBaseResource> itemBaseAssembler) {
		this.itemBaseAssembler = itemBaseAssembler;
	}
	@Override
	public ItemResource toResource(Item entity) {
		if(entity != null){
			
			ItemBaseResource itemBase = itemBaseAssembler.toResource(entity.getItemBase());
			
			return ItemResource.builder()
					.id(entity.getId())
					.itemBase(itemBase)
					.build();
		}
		return null;
	}

	@Override
	public Item fromResource(ItemResource resource) {
		if(resource != null){
			ItemBase itemBase = itemBaseAssembler.fromResource(resource.getItemBaseResource());
			
			return Item.builder()
					.id(resource.getIdentifier())
					.itemBase(itemBase)
					.build();
		}
		return null;
	}

}
