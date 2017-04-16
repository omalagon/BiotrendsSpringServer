package com.biotrends.repositories.oldconsumodao.service;

import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.google.common.io.Resources;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.io.Resources.getResource;
import static java.nio.charset.Charset.defaultCharset;

import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

/**
 * @author Oscar Malagon
 * @since 27/01/2017.
 */
@Service
public class DefaultResourceServiceImpl implements ResourceService{

    @Override
    public String getResourceAsString(String name) {
        return getResourceAsString(null, name);
    }

    @Override
    public String getResourceAsString(Class<?> context, String name) {
        validateName(name);

        try {
            URL url = getResourceAsURL(context, name);

            return Resources.toString(url, defaultCharset());
        } catch (Exception ex) {
            throw new CommonBiotrendsRuntimeException("Error getting Resource as String!", ex);
        }
    }

    @Override
    public URL getResourceAsURL(String name) {
        return getResourceAsURL(null, name);
    }

    @Override
    public URL getResourceAsURL(Class<?> context, String name) {
        validateName(name);

        try {
            Optional<Class<?>> classContext = Optional.ofNullable(context);
            if (classContext.isPresent()) {
                return getResource(classContext.get(), name);
            } else {
                return getResource(name);
            }
        } catch (Exception ex) {
            throw new CommonBiotrendsRuntimeException("Error getting Resource as URL!", ex);
        }
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        return getResourceAsStream(null, name);
    }

    @Override
    public InputStream getResourceAsStream(Class<?> context, String name) {
        validateName(name);

        try {
            URL url = getResourceAsURL(context, name);

            return url.openStream();
        } catch (Exception ex) {
            throw new CommonBiotrendsRuntimeException("Error getting Resource as Stream!", ex);
        }
    }

    private void validateName(String name) {
        checkNotNull(name, "The Name is a required argument!");
    }
}


