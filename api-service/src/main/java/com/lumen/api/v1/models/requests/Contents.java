package com.lumen.api.v1.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Contents {
    @JsonProperty("parts")
    private List<Parts> parts;

    public List<Parts> getParts() {
        return parts;
    }

    public void setParts(List<Parts> parts) {
        this.parts = parts;
    }
}