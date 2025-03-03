package com.lumen.api.v1.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lumen.api.v1.models.responses.gemini.GeminiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Component
public class SerializationUtils {

    @Autowired
    private LogUtils logger;

    public String serializeBase64ToString(String base64) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(base64);
        oos.close();

        String sample = "";

        return sample.replaceAll("\n", " ");
//            return Base64.getEncoder().encodeToString(baos.toByteArray());

    }


    public GeminiResponse serializeDocument(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, GeminiResponse.class);
    }

    public JsonNode serializeToJson(String text) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.readTree(text);
    }
}
