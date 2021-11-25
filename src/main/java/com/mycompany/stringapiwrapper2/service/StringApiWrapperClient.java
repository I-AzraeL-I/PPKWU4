package com.mycompany.stringapiwrapper2.service;

import com.mycompany.stringapiwrapper2.configuration.MediaTypeExtended;
import com.mycompany.stringapiwrapper2.dto.Statistics;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "string-api-wrapper-client", url = "${string-api-wrapper.url}")
public interface StringApiWrapperClient {

    @GetMapping(value = "/statistics", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.TEXT_PLAIN_VALUE,
            MediaTypeExtended.TEXT_CSV_VALUE
    })
    ResponseEntity<Statistics> getStatistics(@SpringQueryMap Map<String, String> params);

    @GetMapping(value = "/is-word", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.TEXT_PLAIN_VALUE,
            MediaTypeExtended.TEXT_CSV_VALUE
    })
    ResponseEntity<Boolean> isAlpha(@SpringQueryMap Map<String, String> params);

    @GetMapping(value = "/is-number", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.TEXT_PLAIN_VALUE,
            MediaTypeExtended.TEXT_CSV_VALUE
    })
    ResponseEntity<Boolean> isNumber(@SpringQueryMap Map<String, String> params);

    @GetMapping(value = "/is-lower", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.TEXT_PLAIN_VALUE,
            MediaTypeExtended.TEXT_CSV_VALUE
    })
    ResponseEntity<Boolean> isLower(@SpringQueryMap Map<String, String> params);

    @GetMapping(value = "/is-upper", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.TEXT_PLAIN_VALUE,
            MediaTypeExtended.TEXT_CSV_VALUE
    })
    ResponseEntity<Boolean> isUpper(@SpringQueryMap Map<String, String> params);
}
