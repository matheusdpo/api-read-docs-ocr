package com.lumen.api.v1.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.lumen.api.v1.exceptions.GeminiException;
import com.lumen.api.v1.models.requests.gemini.Contents;
import com.lumen.api.v1.models.requests.gemini.GeminiRequest;
import com.lumen.api.v1.models.requests.gemini.Parts;
import com.lumen.api.v1.models.responses.gemini.GeminiResponse;
import com.lumen.api.v1.utils.SerializationUtils;
import com.lumen.api.v1.utils.StringUtils;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GeminiService {

    @Autowired
    private SerializationUtils serializationUtils;

    @Autowired
    private StringUtils stringUtils;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @Value("${gemini.api.prompt}")
    private String geminiApiPrompt;

    @Value("${gemini.api.v1.package}")
    private String geminiApiPackage;


    public JsonNode getResponseBody(String base64, String docType, String country) throws GeminiException, IOException {
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

        String text = serializationUtils.serializeBase64ToString(base64);

        String prompt = String.format(geminiApiPrompt, classModelStr, text);

        String responseBody = getResponseBodyStr(prompt);

        GeminiResponse geminiResponse = serializationUtils.serializeDocument(responseBody);

        return serializationUtils.serializeToJson(geminiResponse.getCandidates().getFirst().getContent().getParts().getFirst().getText());
    }

    public boolean isClassExists(String className) {
        try {
            Class.forName(geminiApiPackage + className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public String getResponseBodyStr(String prompt) throws GeminiException {
        try {
            GeminiRequest responseModel = new GeminiRequest();
            Contents contents = new Contents();
            Parts parts = new Parts();

            parts.setText(prompt);
            contents.setParts(List.of(parts));
            responseModel.setContents(List.of(contents));

            String fullUrl = geminiApiUrl + geminiApiKey;

            HttpResponse<String> response = Unirest.post(fullUrl)
                    .header("Content-Type", "application/json")
                    .body(responseModel)
                    .asString();

            return response.getBody();
        } catch (Exception e) {
            throw new GeminiException("Impossible to get response from API Docs");
        }
    }
}
