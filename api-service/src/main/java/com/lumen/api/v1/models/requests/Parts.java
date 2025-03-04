package com.lumen.api.v1.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parts {
    @JsonProperty("text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}