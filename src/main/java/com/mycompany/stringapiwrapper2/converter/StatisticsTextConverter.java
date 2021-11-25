package com.mycompany.stringapiwrapper2.converter;

import com.mycompany.stringapiwrapper2.dto.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StatisticsTextConverter extends AbstractTextConverter<Statistics> {

    @Override
    protected boolean supports(Class<?> clazz) {
        return Statistics.class.isAssignableFrom(clazz);
    }

    @Override
    protected Statistics readInternal(Class<? extends Statistics> clazz, HttpInputMessage inputMessage) throws HttpMessageNotReadableException, IOException {
        String[] lines = new String(inputMessage.getBody().readAllBytes(), StandardCharsets.UTF_8).replaceAll(":", "").split("[\\r\\n]+");
        Map<String, String> fields = Arrays.stream(lines)
                .map(line -> line.split(" "))
                .collect(Collectors.toMap(e -> e[0], e -> e[1]));
        var statistics = new Statistics();
        statistics.isWord = Boolean.parseBoolean(fields.get("isWord"));
        statistics.isNumber = Boolean.parseBoolean(fields.get("isNumber"));
        statistics.isUpper = Boolean.parseBoolean(fields.get("isUpper"));
        statistics.isLower = Boolean.parseBoolean(fields.get("isLower"));
        statistics.characterCount = Long.parseLong(fields.get("characterCount"));
        statistics.letterCount = Long.parseLong(fields.get("letterCount"));
        statistics.digitCount = Long.parseLong(fields.get("digitCount"));
        statistics.lowercaseLetterCount = Long.parseLong(fields.get("lowercaseLetterCount"));
        statistics.uppercaseLetterCount = Long.parseLong(fields.get("uppercaseLetterCount"));
        statistics.whitespaceCount = Long.parseLong(fields.get("whitespaceCount"));
        statistics.specialCharactersCount = Long.parseLong(fields.get("specialCharactersCount"));
        return statistics;
    }

    @Override
    protected void writeInternal(Statistics statistics, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getBody()
                .write(statistics.toString().getBytes());
    }
}
