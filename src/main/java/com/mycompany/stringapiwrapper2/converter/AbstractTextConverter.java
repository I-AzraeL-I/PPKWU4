package com.mycompany.stringapiwrapper2.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

public abstract class AbstractTextConverter<T> extends AbstractHttpMessageConverter<T> {

    private static final MediaType SUPPORTED_MEDIA_TYPE = MediaType.TEXT_PLAIN;

    public AbstractTextConverter() {
        super(SUPPORTED_MEDIA_TYPE);
    }
}
