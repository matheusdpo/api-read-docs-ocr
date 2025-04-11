package com.lumen.api.v1.models.responses.api;

import java.time.LocalDateTime;

public record ResponseErrorApiModel(
        LocalDateTime timestamp,
        String message,
        String detail) {

}
