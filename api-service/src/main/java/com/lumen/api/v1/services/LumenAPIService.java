package com.lumen.api.v1.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.lumen.api.v1.exceptions.GeminiException;
import com.lumen.api.v1.models.responses.gemini.GeminiResponse;
import com.lumen.api.v1.utils.SerializationUtils;
import com.lumen.api.v1.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LumenAPIService {


    @Autowired
    private GeminiService geminiService;

    @Autowired
    private SerializationUtils serializationUtils;

    @Autowired
    private StringUtils stringUtils;

    public JsonNode getResponseBody(String base64, String docType, String mimeType, String country) throws Exception {
        String docTypeModel = "Document" + docType + stringUtils.toCamelCase(country);

        if (!isClassExists(docTypeModel)) {
            throw new GeminiException("Document Model Type not found");
        }

        String classModelStr = "";

        try {
            classModelStr = stringUtils.getClassNameStr(docTypeModel);
        } catch (Exception e) {
            throw new GeminiException("Impossible to get class name");
        }

        String prompt = String.format(geminiService.getGeminiApiPrompt(), classModelStr);

        String responseBody = geminiService.getResponseBodyStr(base64, prompt, mimeType);

        GeminiResponse geminiResponse = serializationUtils.fromJson2Class(responseBody, GeminiResponse.class);

        return serializationUtils.serializeToJson(geminiResponse.getCandidates().getFirst().getContent().getParts().getFirst().getText());
    }

    private boolean isClassExists(String className) {
        try {
            Class.forName(geminiService.getGeminiApiPackage() + className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
