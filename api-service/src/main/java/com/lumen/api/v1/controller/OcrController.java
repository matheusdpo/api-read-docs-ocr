package com.lumen.api.v1.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.lumen.api.v1.services.DocumentService;
import com.lumen.api.v1.services.LumenAPIService;
import com.lumen.api.v1.services.RequestLogsService;
import com.lumen.commons.enums.StatusErrorEnum;
import com.lumen.commons.models.entities.ApiKeyEntity;
import com.lumen.commons.models.entities.UserEntity;
import com.lumen.commons.services.ApiKeyService;
import com.lumen.commons.utils.Base64Utils;
import com.lumen.commons.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1")
public class OcrController {

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

    private static final String ENDPOINT = "/api/v1/ocr";

    @PostMapping("/ocr")
    public ResponseEntity<?> init(@RequestHeader(value = "X-API-KEY", required = false) String apiKey,
                                  @RequestPart("file") MultipartFile file) throws Exception {
        LocalDateTime requestDate = LocalDateTime.now();

        try {
            if (Objects.isNull(apiKey) || apiKey.isBlank()) {
                throw new IllegalArgumentException(StatusErrorEnum.API_KEY_IS_REQUIRED.getError());
            }

            Optional<ApiKeyEntity> apiKeyEntity = apiKeyService.findByKey(apiKey);

            if (!apiKeyService.isApiKeyValid(apiKey)) {
                throw new IllegalArgumentException(StatusErrorEnum.API_KEY_IS_NOT_VALID.getError());
            }

            UserEntity userEntity = apiKeyEntity.get().getUserEntity();

            if (!userEntity.isActive()) {
                throw new IllegalArgumentException(StatusErrorEnum.USER_INACTIVE.getError());
            }

            if (file.isEmpty()) {
                throw new IllegalArgumentException(StatusErrorEnum.FILE_IS_EMPTY.getError());
            }

            if (!documentService.isDocumentSizeValid(file.getSize())) {
                throw new IllegalArgumentException(StatusErrorEnum.FILE_SIZE_IS_NOT_VALID.getError());
            }

            if (!documentService.isExtensionValid(file.getOriginalFilename())) {
                throw new IllegalArgumentException(StatusErrorEnum.FILE_EXTENSION_IS_NOT_VALID.getError());
            }

            String imageBase64 = base64Utils.encode(file);

            String mimeType = file.getContentType();

            if (imageBase64.isEmpty()) {
                throw new IllegalArgumentException("Error converting file to Base64");
            }

            JsonNode response = lumenAPIService.getOcrResponseBody(
                    imageBase64,
                    mimeType
            );

            HttpStatus httpStatusCode = HttpStatus.OK;

            requestLogsService.saveLog(apiKey,
                    ENDPOINT,
                    httpStatusCode,
                    requestDate
            );

            return ResponseEntity
                    .status(httpStatusCode)
                    .body(response);
        } catch (Exception e) {
            logger.error("Error in ControllerApi", e);
            HttpStatusCode httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            requestLogsService.saveLog(apiKey,
                    ENDPOINT,
                    httpStatusCode,
                    requestDate
            );
            throw new RuntimeException(StatusErrorEnum.UNKOWN_ERROR.getError());
        }
    }
}