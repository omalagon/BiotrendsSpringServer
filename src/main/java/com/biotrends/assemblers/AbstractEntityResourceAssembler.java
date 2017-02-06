package com.biotrends.assemblers;

import com.biotrends.entities.BiotrendsBaseEntity;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * @author Oscar Malagon
 * @since 4/02/2017.
 */
public abstract class AbstractEntityResourceAssembler <E extends BiotrendsBaseEntity, R extends EntityResource>
    extends ResourceAssemblerSupport<E, R> implements EntityResourceAssembler<E, R> {

    public AbstractEntityResourceAssembler(Class<?> controller, Class<R> resource) {
        super(controller, resource);
    }

    protected abstract R buildResource(E entity);

    @Override
    public R toResource(E entity) {
        if (entity != null) {
            return createResourceWithId(entity.getId(), entity);
        }

        return null;
    }

    @Override
    protected R instantiateResource(E entity) {
        if (entity != null) {
            return buildResource(entity);
        }

        return null;
    }

}

