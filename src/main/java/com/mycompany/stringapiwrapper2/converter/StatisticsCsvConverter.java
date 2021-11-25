package com.mycompany.stringapiwrapper2.converter;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.mycompany.stringapiwrapper2.dto.Statistics;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StatisticsCsvConverter extends AbstractCsvConverter<Statistics> {

    @Override
    protected boolean supports(Class<?> clazz) {
        return Statistics.class.isAssignableFrom(clazz);
    }

    @Override
    protected Statistics readInternal(Class<? extends Statistics> clazz, HttpInputMessage inputMessage) throws HttpMessageNotReadableException, IOException {
        var csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper
                .schemaFor(Statistics.class)
                .withHeader();
        return csvMapper
                .readerFor(Statistics.class)
                .with(schema)
                .readValue(inputMessage.getBody());
    }

    @Override
    protected void writeInternal(Statistics statistics, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        var csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper
                .schemaFor(Statistics.class)
                .withHeader();
        String csv = csvMapper.writer(schema)
                .writeValueAsString(statistics);
        outputMessage.getBody()
                .write(csv.getBytes());
    }
}
