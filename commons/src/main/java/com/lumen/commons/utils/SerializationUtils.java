package com.lumen.commons.utils;

import com.lumen.commons.exceptions.SerializationUtilsException;
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

    public <T> T fromJson2Class(String json, Class<T> modelClass) throws SerializationUtilsException {
        try {
            return new ObjectMapper().readValue(json, modelClass);
        } catch (JsonProcessingException e) {
            throw new SerializationUtilsException("Erro ao deserializar objeto", e);
        }
    }
}
