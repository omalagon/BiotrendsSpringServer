package com.biotrends.api.item.util;

import static org.springframework.hateoas.Link.REL_SELF;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;
import static org.springframework.http.HttpStatus.Series;
import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
/**
 * @author Oscar Malagon
 * @since 23/12/2016.
 */
public final class RestConstants {

    public static final String APPLICATION_HAL_JSON_VALUE = "application/hal+json";
    public static final String APPLICATION_JSON_AND_HAL_JSON_VALUE = APPLICATION_HAL_JSON_VALUE	+ "," + APPLICATION_JSON_VALUE;

    private static final List<HttpStatus> ACCEPTED_STATUS = Arrays.asList(
        OK, ACCEPTED, PRECONDITION_FAILED, EXPECTATION_FAILED, NOT_FOUND, BAD_REQUEST
    );

    private RestConstants() {
    }

    public static HttpHeaders getLocationHeaderFromResource(Resource<?> resource) {
        HttpHeaders headers = new HttpHeaders();

        Link self = resource.getLink(REL_SELF);
        if (self != null) {
            headers.setLocation(URI.create(self.getHref()));
        }

        return headers;
    }

    public static boolean isError(HttpStatus status) {
        if (ACCEPTED_STATUS.contains(status)) {
            return false;
        }

        Series series = status.series();

        return CLIENT_ERROR.equals(series) || SERVER_ERROR.equals(series);
    }

}
