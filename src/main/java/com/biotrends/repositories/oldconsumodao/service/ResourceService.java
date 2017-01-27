package com.biotrends.repositories.oldconsumodao.service;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Oscar Malagon
 * @since 27/01/2017.
 */
public interface ResourceService {

    String getResourceAsString(String name);

    String getResourceAsString(Class context, String name);

    URL getResourceAsURL(String name);

    URL getResourceAsURL(Class context, String name);

    InputStream getResourceAsStream(String name);

    InputStream getResourceAsStream(Class context, String name);
}
