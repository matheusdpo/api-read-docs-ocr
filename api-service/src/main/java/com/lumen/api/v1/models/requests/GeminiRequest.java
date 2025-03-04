package com.lumen.api.v1.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GeminiRequest {
    @JsonProperty("contents")
    private List<Contents> contents;

    public List<Contents> getContents() {
        return contents;
    }

    public void setContents(List<Contents> contents) {
        this.contents = contents;
    }
}