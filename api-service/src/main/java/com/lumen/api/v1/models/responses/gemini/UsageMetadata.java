package com.lumen.api.v1.models.responses.gemini;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class UsageMetadata {
    @JsonProperty("promptTokenCount")
    private int promptTokenCount;

    @JsonProperty("candidatesTokenCount")
    private int candidatesTokenCount;

    @JsonProperty("totalTokenCount")
    private int totalTokenCount;

    @JsonProperty("promptTokensDetails")
    private List<TokenDetail> promptTokensDetails;

    @JsonProperty("candidatesTokensDetails")
    private List<TokenDetail> candidatesTokensDetails;
}
