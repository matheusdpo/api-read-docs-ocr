package com.lumen.api.v1.utils;

import com.lumen.api.v1.enums.CountriesEnum;
import com.lumen.api.v1.enums.DocumentTypeEnum;
import com.lumen.api.v1.models.requests.api.RequestBodyOcrModel;
import com.lumen.api.v1.services.DocumentServices;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CheckModelsUtils {

    @Autowired
    private DocumentServices documentServices;

    public boolean isRequestParamsOcrNullOrEmpty(RequestBodyOcrModel requestBodyOcrModel) {
        if (Objects.isNull(requestBodyOcrModel.getCountry()) || StringUtils.isEmpty(requestBodyOcrModel.getCountry())) {
            return true;
        }

        if (Objects.isNull(requestBodyOcrModel.getDocumentType()) || StringUtils.isEmpty(requestBodyOcrModel.getDocumentType())) {
            return true;
        }

        if (Objects.isNull(requestBodyOcrModel.getImageBase64()) || StringUtils.isEmpty(requestBodyOcrModel.getImageBase64())) {
            return true;
        }

        return false;
    }

    public boolean isValidDocumentForCountry(String documentType, String country) {
        DocumentTypeEnum docType = DocumentTypeEnum.valueOf(documentType.toUpperCase());
        CountriesEnum countryEnum = CountriesEnum.valueOf(country.toUpperCase());

        return documentServices.isDocumentTypeAndCountry(docType.getValue(), countryEnum.getValue());
    }
}
