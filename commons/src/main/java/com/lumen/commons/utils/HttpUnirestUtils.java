package com.lumen.commons.utils;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.springframework.stereotype.Component;

@Component
public class HttpUnirestUtils {

    public HttpResponse<String> post(String url, String apiKey, String body) throws UnirestException {
        return post(url + apiKey, body);
    }

    public HttpResponse<String> post(String url, String body) throws UnirestException {
        return Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(body)
                .asString();
    }
}