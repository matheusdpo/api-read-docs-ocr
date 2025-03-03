package com.lumen.api.v1.utils;

import org.springframework.stereotype.Component;

@Component
public class StringUtils {

    public String toCamelCase(String input) {
        return input == null ? null : java.util.Arrays.stream(input.split("[_\\-]"))
                .map(word -> word.isEmpty() ? word : word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(java.util.stream.Collectors.joining());
    }
}
