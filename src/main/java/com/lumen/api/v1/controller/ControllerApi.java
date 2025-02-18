package com.lumen.api.v1.controller;

import com.lumen.api.v1.enums.CountriesEnum;
import com.lumen.api.v1.enums.DocumentTypeEnum;
import com.lumen.api.v1.enums.StatusErrorEnum;
import com.lumen.api.v1.models.RequestBodyOcrModel;
import com.lumen.api.v1.models.ResponseErrorApi;
import com.lumen.api.v1.utils.CheckModelsUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class ControllerApi {

    @GetMapping()
    public ResponseEntity<?> init(@RequestBody RequestBodyOcrModel requestBodyOcrModel) {

        if (CheckModelsUtils.isRequestParamsOcrNullOrEmpty(requestBodyOcrModel)) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(400))
                    .body(new ResponseErrorApi(
                            StatusErrorEnum.PARAMS_NULL_OR_EMPTY.getId(),
                            StatusErrorEnum.PARAMS_NULL_OR_EMPTY.getError()
                    ));
        }

        if (!CountriesEnum.isCountryEqualsEnum(requestBodyOcrModel.getCountry())) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(400))
                    .body(new ResponseErrorApi(
                            StatusErrorEnum.COUNTRY_IS_NOT_AVAILABLE.getId(),
                            StatusErrorEnum.COUNTRY_IS_NOT_AVAILABLE.getError()
                    ));
        }

        if (!DocumentTypeEnum.isDocumentEqualsEnum(requestBodyOcrModel.getDocumentType())) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(400))
                    .body(new ResponseErrorApi(
                            StatusErrorEnum.DOC_IS_NOT_AVAILABLE.getId(),
                            StatusErrorEnum.DOC_IS_NOT_AVAILABLE.getError()
                    ));
        }

        if (!CheckModelsUtils.isValidDocumentForCountry(requestBodyOcrModel.getDocumentType(), requestBodyOcrModel.getCountry())) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(400))
                    .body(new ResponseErrorApi(
                            StatusErrorEnum.DOC_IS_NOT_VALID.getId(),
                            StatusErrorEnum.DOC_IS_NOT_VALID.getError()
                    ));
        }

        return ResponseEntity
                .status(HttpStatusCode.valueOf(200))
                .body(requestBodyOcrModel);
    }
}