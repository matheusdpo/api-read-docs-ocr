package com.lumen.api.v1.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.lumen.api.v1.enums.CountriesEnum;
import com.lumen.api.v1.enums.DocumentTypeEnum;
import com.lumen.api.v1.enums.StatusErrorEnum;
import com.lumen.api.v1.models.requests.api.RequestBodyOcrModel;
import com.lumen.api.v1.models.responses.api.ResponseErrorApiModel;
import com.lumen.api.v1.services.GeminiService;
import com.lumen.api.v1.utils.CheckModelsUtils;
import com.lumen.api.v1.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class ControllerApi {

    @Autowired
    private GeminiService geminiService;

    @Autowired
    private CheckModelsUtils checkModelsUtils;

    @Autowired
    private LogUtils logger;

    @GetMapping()
    public ResponseEntity<?> init(@RequestBody RequestBodyOcrModel requestBodyOcrModel) {
        try {
            if (checkModelsUtils.isRequestParamsOcrNullOrEmpty(requestBodyOcrModel)) {
                return ResponseEntity
                        .status(HttpStatusCode.valueOf(400))
                        .body(new ResponseErrorApiModel(
                                StatusErrorEnum.PARAMS_NULL_OR_EMPTY.getId(),
                                StatusErrorEnum.PARAMS_NULL_OR_EMPTY.getError())
                        );
            }

            if (!checkModelsUtils.isValidDocumentForCountry(requestBodyOcrModel.getDocumentType(), requestBodyOcrModel.getCountry())) {
                return ResponseEntity
                        .status(HttpStatusCode.valueOf(400))
                        .body(new ResponseErrorApiModel(
                                StatusErrorEnum.DOC_IS_NOT_VALID.getId(),
                                StatusErrorEnum.DOC_IS_NOT_VALID.getError())
                        );
            }

            if (!CountriesEnum.isCountryEqualsEnum(requestBodyOcrModel.getCountry())) {
                return ResponseEntity
                        .status(HttpStatusCode.valueOf(400))
                        .body(new ResponseErrorApiModel(
                                StatusErrorEnum.COUNTRY_IS_NOT_AVAILABLE.getId(),
                                StatusErrorEnum.COUNTRY_IS_NOT_AVAILABLE.getError())
                        );
            }

            if (!DocumentTypeEnum.isDocumentEqualsEnum(requestBodyOcrModel.getDocumentType())) {
                return ResponseEntity
                        .status(HttpStatusCode.valueOf(400))
                        .body(new ResponseErrorApiModel(
                                StatusErrorEnum.DOC_IS_NOT_AVAILABLE.getId(),
                                StatusErrorEnum.DOC_IS_NOT_AVAILABLE.getError())
                        );
            }

            JsonNode response = geminiService.getResponseBody(
                    requestBodyOcrModel.getImageBase64(),
                    requestBodyOcrModel.getDocumentType(),
                    requestBodyOcrModel.getCountry()
            );

            logger.info("OK");
            
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(200))
                    .body(response);

        } catch (Exception e) {
            logger.error("Error in ControllerApi", e);
            return ResponseEntity.status(HttpStatusCode.valueOf(500))
                    .body(new ResponseErrorApiModel(
                            StatusErrorEnum.UNKOWN_ERROR.getId(),
                            StatusErrorEnum.UNKOWN_ERROR.getError())
                    );
        }
    }

}