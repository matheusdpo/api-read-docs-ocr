package com.lumen.api.v1.models.responses.gemini;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Part {
    @JsonProperty("text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}