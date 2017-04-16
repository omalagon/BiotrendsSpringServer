package com.biotrends.assemblers.item;

import com.biotrends.assemblers.EntityResource;
import com.biotrends.assemblers.itembase.ItemBaseResource;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Oscar Malagon
 * @since 14/04/2017
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ItemResource", description = "The item Resource Representation")
public class ItemResource extends EntityResource{

	private static final long serialVersionUID = -567759045347243083L;

	@JsonProperty
	private ItemBaseResource itemBaseResource;
	
	@Builder
	public static ItemResource target(String id, ItemBaseResource itemBase){
		ItemResource item = new ItemResource();
		item.setIdentifier(id);
		item.setItemBaseResource(itemBase);
		
		return item;
	}
	
}
