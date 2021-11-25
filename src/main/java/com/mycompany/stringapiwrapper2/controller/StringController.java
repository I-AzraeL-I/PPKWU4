package com.mycompany.stringapiwrapper2.controller;

import com.mycompany.stringapiwrapper2.configuration.MediaTypeExtended;
import com.mycompany.stringapiwrapper2.dto.Statistics;
import com.mycompany.stringapiwrapper2.exception.IncorrectMediaTypeException;
import com.mycompany.stringapiwrapper2.service.StringApiWrapperClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api", produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
                MediaType.TEXT_PLAIN_VALUE,
                MediaTypeExtended.TEXT_CSV_VALUE
})
@RequiredArgsConstructor
public class StringController {

    private final StringApiWrapperClient stringApiClient;

    @GetMapping(value = "/statistics")
    public ResponseEntity<Statistics> getStatistics(@RequestParam String data, @RequestParam String requestFormat, @RequestParam String responseFormat) {
        var params = Map.of(
                "data", data,
                "format", requestFormat);
        var body = stringApiClient.getStatistics(params).getBody();
        var mediaType = parseMediaType(responseFormat);
        return responseWithMediaType(body, mediaType);
    }

    @GetMapping(value = "/is-word")
    public ResponseEntity<Boolean> isAlpha(@RequestParam String data, @RequestParam String requestFormat, @RequestParam String responseFormat) {
        var params = Map.of(
                "data", data,
                "format", requestFormat);
        var body = stringApiClient.isAlpha(params).getBody();
        var mediaType = parseMediaType(responseFormat);
        return responseWithMediaType(body, mediaType);
    }

    @GetMapping(value = "/is-number")
    public ResponseEntity<Boolean> isNumber(@RequestParam String data, @RequestParam String requestFormat, @RequestParam String responseFormat) {
        var params = Map.of(
                "data", data,
                "format", requestFormat);
        var body = stringApiClient.isNumber(params).getBody();
        var mediaType = parseMediaType(responseFormat);
        return responseWithMediaType(body, mediaType);
    }

    @GetMapping(value = "/is-lower")
    public ResponseEntity<Boolean> isLower(@RequestParam String data, @RequestParam String requestFormat, @RequestParam String responseFormat) {
        var params = Map.of(
                "data", data,
                "format", requestFormat);
        var body = stringApiClient.isLower(params).getBody();
        var mediaType = parseMediaType(responseFormat);
        return responseWithMediaType(body, mediaType);
    }

    @GetMapping(value = "/is-upper")
    public ResponseEntity<Boolean> isUpper(@RequestParam String data, @RequestParam String requestFormat, @RequestParam String responseFormat) {
        var params = Map.of(
                "data", data,
                "format", requestFormat);
        var body = stringApiClient.isUpper(params).getBody();
        var mediaType = parseMediaType(responseFormat);
        return responseWithMediaType(body, mediaType);
    }

    @PostMapping(value = "/convert/statistics", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.TEXT_PLAIN_VALUE,
            MediaTypeExtended.TEXT_CSV_VALUE
    })
    public ResponseEntity<Statistics> convertStatistics(@RequestBody Statistics statistics, @RequestParam String responseFormat) {
        var mediaType = parseMediaType(responseFormat);
        return responseWithMediaType(statistics, mediaType);
    }

    @PostMapping(value = "/convert/boolean", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE,
            MediaType.TEXT_PLAIN_VALUE,
            MediaTypeExtended.TEXT_CSV_VALUE
    })
    public ResponseEntity<Boolean> convertBoolean(@RequestBody Boolean aBoolean, @RequestParam String responseFormat) {
        var mediaType = parseMediaType(responseFormat);
        return responseWithMediaType(aBoolean, mediaType);
    }

    private <T> ResponseEntity<T> responseWithMediaType(T body, MediaType mediaType) {
        var headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(body);
    }

    private MediaType parseMediaType(String format) {
        switch (format) {
            case "json":
                return MediaType.APPLICATION_JSON;
            case "xml":
                return MediaType.APPLICATION_XML;
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "csv":
                return MediaTypeExtended.TEXT_CSV;
            default:
                throw new IncorrectMediaTypeException(format, "Given format is not supported");
        }
    }
}
