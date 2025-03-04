package com.lumen.api.v1.models.responses.ocrspace;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseOcrSpaceModel {


    @JsonProperty("ParsedResults")
    private List<ParsedResult> parsedResults; // Agora é uma lista


    @JsonProperty("IsErroredOnProcessing")
    private boolean isErroredOnProcessing;


    @JsonProperty("OCRExitCode")
    private Integer ocrExitCode;

    // Getters
    public List<ParsedResult> getParsedResults() {
        return parsedResults;
    }

    public boolean isErroredOnProcessing() {
        return isErroredOnProcessing;
    }

    public Integer getOcrExitCode() {
        return ocrExitCode;
    }
}
