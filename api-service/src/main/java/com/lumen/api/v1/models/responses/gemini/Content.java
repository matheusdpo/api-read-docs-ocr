package com.lumen.api.v1.models.responses.gemini;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Content {
    @JsonProperty("parts")
    private List<Part> parts;

    @JsonProperty("role")
    private String role;

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}