package com.lumen.api.v1.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.lumen.api.v1.exceptions.GeminiException;
import com.lumen.api.v1.models.ResponseOcrModel;
import com.lumen.api.v1.models.responses.api.ResponseCheckSignatureModel;
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

    public JsonNode getReadDocsResponseBody(String base64, String docType, String mimeType, String country) throws Exception {
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

        String prompt = String.format(geminiService.getGeminiApiReadDocsPrompt(), classModelStr);

        String responseBody = geminiService.getResponseBodyStr(base64, prompt, mimeType);

        GeminiResponse geminiResponse = serializationUtils.fromJson2Class(responseBody, GeminiResponse.class);

        return serializationUtils.serializeToJson(
                geminiResponse.getCandidates()
                        .getFirst()
                        .getContent()
                        .getParts()
                        .getFirst()
                        .getText()
                        .replaceAll("`", "")
                        .replaceAll("json", "")
                        .trim()
        );
    }

    public JsonNode getCheckSignatureResponseBody(String base64, String mimeType) throws Exception {
        ResponseCheckSignatureModel responseCheckSignatureModel = new ResponseCheckSignatureModel();

        String prompt = String.format(geminiService.getGeminiApiCheckSignaturePrompt(), responseCheckSignatureModel.toString());

        String responseBody = geminiService.getResponseBodyStr(base64, prompt, mimeType);

        GeminiResponse geminiResponse = serializationUtils.fromJson2Class(responseBody, GeminiResponse.class);

        return serializationUtils.serializeToJson(
                geminiResponse.getCandidates()
                        .getFirst()
                        .getContent()
                        .getParts()
                        .getFirst()
                        .getText()
                        .replaceAll("`", "")
                        .replaceAll("json", "")
                        .trim()
        );
    }


    public JsonNode getOcrResponseBody(String base64, String mimeType) throws Exception {
        ResponseOcrModel responseCheckSignatureModel = new ResponseOcrModel();

        String prompt = String.format(geminiService.getGeminiApiOcrPrompt(), responseCheckSignatureModel.toString());

        String responseBody = geminiService.getResponseBodyStr(base64, prompt, mimeType);

        GeminiResponse geminiResponse = serializationUtils.fromJson2Class(responseBody, GeminiResponse.class);

        return serializationUtils.serializeToJson(
                geminiResponse.getCandidates()
                        .getFirst()
                        .getContent()
                        .getParts()
                        .getFirst()
                        .getText()
                        .replaceAll("`", "")
                        .replaceAll("json", "")
                        .trim()
        );
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
