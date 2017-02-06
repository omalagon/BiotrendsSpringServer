package com.biotrends.assemblers;

/**
 * @author Oscar Malagon
 * @since 4/02/2017.
 */
public interface ResourceAssembler <E, R> extends ResourceDisassembler<E, R> {

    R toResource(E entity);

}
