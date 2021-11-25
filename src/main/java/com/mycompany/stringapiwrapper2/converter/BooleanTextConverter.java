package com.mycompany.stringapiwrapper2.converter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class BooleanTextConverter extends AbstractTextConverter<Boolean> {

    @Override
    protected boolean supports(Class<?> clazz) {
        return Boolean.class.isAssignableFrom(clazz);
    }

    @Override
    protected Boolean readInternal(Class<? extends Boolean> clazz, HttpInputMessage inputMessage) throws HttpMessageNotReadableException, IOException {
        var stringObject = new String(inputMessage.getBody().readAllBytes(), StandardCharsets.UTF_8);
        return Boolean.parseBoolean(stringObject);
    }

    @Override
    protected void writeInternal(Boolean aBoolean, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getBody()
                .write(aBoolean.toString().getBytes());
    }
}
