package com.lumen.api.v1.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.lumen.api.v1.enums.CountriesEnum;
import com.lumen.api.v1.enums.DocumentTypeEnum;
import com.lumen.api.v1.enums.StatusErrorEnum;
import com.lumen.api.v1.models.responses.api.ResponseErrorApiModel;
import com.lumen.api.v1.services.ApiKeyService;
import com.lumen.api.v1.services.DocumentService;
import com.lumen.api.v1.services.GeminiService;
import com.lumen.api.v1.utils.Base64Utils;
import com.lumen.api.v1.utils.LogUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Controller
@RequestMapping("/api/v1")
public class ApiController {

    @Autowired
    private GeminiService geminiService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ApiKeyService apiKeyService;

    @Autowired
    private Base64Utils base64Utils;

    @Autowired
    private LogUtils logger;

    @PostMapping()
    public ResponseEntity<?> init(@RequestHeader(value = "X-API-KEY", required = false) String apiKey,
                                  @RequestPart("file") @NotNull MultipartFile file,
                                  @RequestParam("country") @NotNull @NotEmpty String country,
                                  @RequestParam("docType") @NotNull @NotEmpty String docType) {
        try {
            if (Objects.isNull(apiKey) || apiKey.isBlank()) {
                return buildErrorResponse(StatusErrorEnum.API_KEY_IS_REQUIRED, HttpStatus.BAD_REQUEST);
            }

            if (!apiKeyService.isApiKeyValid(apiKey)) {
                return buildErrorResponse(StatusErrorEnum.API_KEY_IS_NOT_VALID, HttpStatus.BAD_REQUEST);
            }


            if (!CountriesEnum.isCountryEqualsEnum(country)) {
                return buildErrorResponse(StatusErrorEnum.COUNTRY_IS_NOT_AVAILABLE, HttpStatus.BAD_REQUEST);
            }

            if (!DocumentTypeEnum.isDocumentEqualsEnum(docType)) {
                return buildErrorResponse(StatusErrorEnum.DOC_IS_NOT_VALID, HttpStatus.BAD_REQUEST);
            }

            if (!documentService.isDocumentTypeAndCountry(docType, country)) {
                return buildErrorResponse(StatusErrorEnum.DOC_IS_NOT_VALID, HttpStatus.BAD_REQUEST);
            }

            String imageBase64 = base64Utils.encode(file);

            if (imageBase64.isEmpty()) {
                return buildErrorResponse(StatusErrorEnum.BASE64_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            JsonNode response = geminiService.getResponseBody(
                    imageBase64,
                    docType,
                    country
            );

            return ResponseEntity
                    .status(HttpStatusCode.valueOf(200))
                    .body(response);

        } catch (Exception e) {
            logger.error("Error in ControllerApi", e);
            return buildErrorResponse(StatusErrorEnum.UNKOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<ResponseErrorApiModel> buildErrorResponse(StatusErrorEnum error, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(new ResponseErrorApiModel(error.getId(), error.getError()));
    }
}