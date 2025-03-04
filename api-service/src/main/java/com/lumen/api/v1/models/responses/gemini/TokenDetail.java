package com.lumen.api.v1.models.responses.gemini;

import com.fasterxml.jackson.annotation.JsonProperty;

public
class TokenDetail {
    @JsonProperty("modality")
    private String modality;

    @JsonProperty("tokenCount")
    private int tokenCount;
}