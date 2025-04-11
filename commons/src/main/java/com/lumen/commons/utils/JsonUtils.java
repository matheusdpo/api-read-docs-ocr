package com.lumen.commons.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

@Component
public class JsonUtils {

    public String jsonGeminiBuilder(String base64, String prompt, String mimeType) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode rootNode = objectMapper.createObjectNode();

        ArrayNode contentsArray = objectMapper.createArrayNode();

        ObjectNode contentObject = objectMapper.createObjectNode();

        ArrayNode partsArray = objectMapper.createArrayNode();

        ObjectNode textPart = objectMapper.createObjectNode();
        textPart.put("text", prompt);
        partsArray.add(textPart);

        ObjectNode inlineDataPart = objectMapper.createObjectNode();
        ObjectNode inlineData = objectMapper.createObjectNode();
        inlineData.put("mime_type", mimeType);
        inlineData.put("data", base64);
        inlineDataPart.set("inline_data", inlineData);
        partsArray.add(inlineDataPart);

        contentObject.set("parts", partsArray);

        contentsArray.add(contentObject);

        rootNode.set("contents", contentsArray);

        String jsonString = rootNode.toPrettyString();

        return jsonString;
    }
}
