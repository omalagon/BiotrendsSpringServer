package com.biotrends.exceptions;

/**
 * @author Oscar Malagon
 * @since 15/01/2017.
 */
public class CommonBiotrendsRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -2906468147701671472L;

	public CommonBiotrendsRuntimeException() {
        super();
    }

    public CommonBiotrendsRuntimeException(String message) {
        super(message);
    }

    public CommonBiotrendsRuntimeException(String message, Throwable cause){
        super(message, cause);
    }

    public CommonBiotrendsRuntimeException(Throwable cause){
        super(cause);
    }
}
