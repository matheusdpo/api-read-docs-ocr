package com.lumen.api.v1.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.lumen.api.v1.entities.ApiKeyEntity;
import com.lumen.api.v1.entities.UserEntity;
import com.lumen.api.v1.enums.CountriesEnum;
import com.lumen.api.v1.enums.DocumentTypeEnum;
import com.lumen.api.v1.enums.StatusErrorEnum;
import com.lumen.api.v1.models.responses.api.ResponseErrorApiModel;
import com.lumen.api.v1.services.ApiKeyService;
import com.lumen.api.v1.services.DocumentService;
import com.lumen.api.v1.services.LumenAPIService;
import com.lumen.api.v1.services.RequestLogsService;
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

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1")
public class ReadDocsController {

    @Autowired
    private LumenAPIService lumenAPIService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ApiKeyService apiKeyService;

    @Autowired
    private RequestLogsService requestLogsService;

    @Autowired
    private Base64Utils base64Utils;

    @Autowired
    private LogUtils logger;

    private static final String ENDPOINT = "/api/v1/read-docs";

    @Autowired
    private UserEntityRepository userEntityRepository;

    @PostMapping("/read-docs")
    public ResponseEntity<?> init(@RequestHeader(value = "X-API-KEY", required = false) String apiKey,
                                  @RequestPart("file") @NotNull MultipartFile file,
                                  @RequestParam("country") @NotNull @NotEmpty String country,
                                  @RequestParam("docType") @NotNull @NotEmpty String docType) {
        LocalDateTime requestDate = LocalDateTime.now();

        try {
            if (Objects.isNull(apiKey) || apiKey.isBlank()) {
                return buildErrorResponse(StatusErrorEnum.API_KEY_IS_REQUIRED, HttpStatus.BAD_REQUEST);
            }

            Optional<ApiKeyEntity> apiKeyEntity = apiKeyService.findByKey(apiKey);

            if (!apiKeyService.isApiKeyValid(apiKey)) {
                return buildErrorResponse(StatusErrorEnum.API_KEY_IS_NOT_VALID, HttpStatus.BAD_REQUEST);
            }

            UserEntity userEntity = apiKeyEntity.get().getUserEntity();

            if (!userEntity.isActive()) {
                return buildErrorResponse(StatusErrorEnum.USER_INACTIVE, HttpStatus.BAD_REQUEST);
            }

            if (file.isEmpty()) {
                return buildErrorResponse(StatusErrorEnum.FILE_IS_EMPTY, HttpStatus.BAD_REQUEST);
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

            if (!documentService.isDocumentSizeValid(file.getSize())) {
                return buildErrorResponse(StatusErrorEnum.FILE_SIZE_IS_NOT_VALID, HttpStatus.BAD_REQUEST);
            }

            if (!documentService.isExtensionValid(file.getOriginalFilename())) {
                return buildErrorResponse(StatusErrorEnum.FILE_EXTENSION_IS_NOT_VALID, HttpStatus.BAD_REQUEST);
            }

            String imageBase64 = base64Utils.encode(file);

            String mimeType = file.getContentType();

            if (imageBase64.isEmpty()) {
                return buildErrorResponse(StatusErrorEnum.BASE64_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            JsonNode response = lumenAPIService.getReadDocsResponseBody(
                    imageBase64,
                    docType,
                    mimeType,
                    country
            );

            HttpStatus httpStatusCode = HttpStatus.OK;

            requestLogsService.saveLog(apiKey,
                    ENDPOINT,
                    httpStatusCode,
                    requestDate
            );
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(200))
                    .body(response);
        } catch (Exception e) {
            logger.error("Error in ControllerApi", e);
            HttpStatusCode httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            requestLogsService.saveLog(apiKey,
                    ENDPOINT,
                    httpStatusCode,
                    requestDate
            );
            return buildErrorResponse(StatusErrorEnum.UNKOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<ResponseErrorApiModel> buildErrorResponse(StatusErrorEnum error, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(new ResponseErrorApiModel(error.getId(), error.getError()));
    }
}