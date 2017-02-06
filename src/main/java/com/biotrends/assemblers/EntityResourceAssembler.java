package com.biotrends.assemblers;

import com.biotrends.entities.BiotrendsBaseEntity;
import org.springframework.hateoas.ResourceAssembler;

/**
 * @author Oscar Malagon
 * @since 4/02/2017.
 */
public interface EntityResourceAssembler <E extends BiotrendsBaseEntity, R extends EntityResource>
    extends ResourceAssembler<E, R>, ResourceDisassembler<E, R>{
}
