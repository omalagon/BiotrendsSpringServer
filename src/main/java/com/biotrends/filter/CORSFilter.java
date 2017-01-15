package com.biotrends.filter;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.springframework.http.HttpMethod.OPTIONS;

/**
 * @author Oscar Malagon
 * @since 10/01/2017.
 */
@Component
@Order
public class CORSFilter extends GenericFilterBean {

    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    private static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";
    private static final String ACCESS_CONTROL_REQUEST_METHOD = "Access-Control-Request-Method";
    private static final String ACCESS_CONTROL_REQUEST_HEADERS = "Access-Control-Request-Headers";
    private static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";

    private static final String CACHE_SECONDS = "300";
    private static final String LOCATION = "Location";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws
        IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");

        String requestMethod = request.getHeader(ACCESS_CONTROL_REQUEST_METHOD);
        if (OPTIONS.toString().equals(request.getMethod()) && !isNullOrEmpty(requestMethod)) {
            String methods = HttpMethod.valueOf(requestMethod.toUpperCase()).toString();

            response.addHeader(ACCESS_CONTROL_ALLOW_METHODS, methods);
            response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, request.getHeader(ACCESS_CONTROL_REQUEST_HEADERS));
            response.setHeader(ACCESS_CONTROL_MAX_AGE, CACHE_SECONDS);
        } else {
            response.addHeader(ACCESS_CONTROL_EXPOSE_HEADERS, LOCATION);
        }

        chain.doFilter(req, res);
    }

}
