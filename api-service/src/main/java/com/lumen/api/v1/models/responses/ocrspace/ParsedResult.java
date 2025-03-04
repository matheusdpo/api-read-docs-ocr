package com.lumen.api.v1.models.responses.ocrspace;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ParsedResult {


    @JsonProperty("ParsedText")
    private String parsedText;

    // Getters
    public String getParsedText() {
        return parsedText;
    }
}
