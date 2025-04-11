package com.lumen.commons.models.responses;

import java.time.LocalDateTime;

public record ResponseErrorApiModel(
        LocalDateTime timestamp,
        String message,
        String detail) {

}
