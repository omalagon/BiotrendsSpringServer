package com.biotrends.utils;

import lombok.Builder;
import lombok.Data;

/**
 * @author Oscar Malagon
 * @since 15/01/2017.
 */
@Data
public class StringResponse {

    private String response;

    @Builder
    public static StringResponse target(String response){
        StringResponse stringResponse = new StringResponse();
        stringResponse.setResponse(response);

        return stringResponse;
    }
}
