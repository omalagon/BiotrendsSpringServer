package com.biotrends.assemblers;

/**
 * @author Oscar Malagon
 * @since 4/02/2017.
 */
@FunctionalInterface
public interface ResourceDisassembler<E, R> {

    E fromResource(R resource);

}

