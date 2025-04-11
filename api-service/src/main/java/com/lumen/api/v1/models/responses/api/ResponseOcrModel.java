package com.lumen.api.v1.models.responses.api;

public class ResponseOcrModel {
    private String text;

    @Override
    public String toString() {
        return "{" +
                "text='" + text + '\'' +
                '}';
    }


    // Getters e Setters

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
