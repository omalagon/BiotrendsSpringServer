package com.biotrends.assemblers.itembase;

import com.biotrends.assemblers.ResourceAssembler;
import com.biotrends.assemblers.ResourceDisassembler;
import com.biotrends.entities.item.ItemBase;

/**
 * @author Oscar Malagon
 * @since 15/04/2017
 *
 */
public interface ItemBaseAssembler <E extends ItemBase, R extends ItemBaseResource>
	      extends ResourceAssembler<E, R>, ResourceDisassembler<E, R>{

}
