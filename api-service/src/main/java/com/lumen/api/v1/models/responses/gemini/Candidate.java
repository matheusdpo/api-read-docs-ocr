package com.lumen.api.v1.models.responses.gemini;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Candidate {
    @JsonProperty("content")
    private Content content;

    @JsonProperty("finishReason")
    private String finishReason;

    @JsonProperty("avgLogprobs")
    private double avgLogprobs;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    public double getAvgLogprobs() {
        return avgLogprobs;
    }

    public void setAvgLogprobs(double avgLogprobs) {
        this.avgLogprobs = avgLogprobs;
    }
}