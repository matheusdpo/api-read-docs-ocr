package com.lumen.api.v1.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

@Component
public class SerializationUtils {

    public JsonNode serializeToJson(String text) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.readTree(text);
    }

    public <T> T fromJson2Class(String json, Class<T> modelClass) throws Exception {
        try {
            return new ObjectMapper().readValue(json, modelClass);
        } catch (Exception e) {
            throw new Exception("Erro ao deserializar objeto", e);
        }
    }
}
