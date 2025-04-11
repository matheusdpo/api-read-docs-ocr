package com.lumen.commons.exceptions.handler;

import com.lumen.commons.exceptions.GeminiException;
import com.lumen.commons.exceptions.PayPalServiceException;
import com.lumen.commons.exceptions.SerializationUtilsException;
import com.lumen.commons.models.responses.ResponseErrorApiModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class CustomResponseHandler extends ResponseEntityExceptionHandler {

    //add PayPalRESTException,  MessagingException
    @ExceptionHandler({Exception.class, GeminiException.class, SerializationUtilsException.class, JsonProcessingException.class, PayPalServiceException.class, ClassNotFoundException.class, IOException.class})
    public ResponseEntity<?> handleAllException(Exception e, WebRequest webRequest) {
        ResponseErrorApiModel responseErrorApiModel = new ResponseErrorApiModel(
                LocalDateTime.now(),
                e.getMessage(),
                webRequest.getDescription(false)
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseErrorApiModel);
    }

    @ExceptionHandler({IllegalArgumentException.class, FileNotFoundException.class})
    public ResponseEntity<?> handleUserException(Exception e, WebRequest webRequest) {
        ResponseErrorApiModel responseErrorApiModel = new ResponseErrorApiModel(
                LocalDateTime.now(),
                e.getMessage(),
                webRequest.getDescription(false)
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseErrorApiModel);
    }
}
