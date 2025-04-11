package com.lumen.commons.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
public class StringUtils {

    public String toCamelCase(String input) {
        return input == null ? null : java.util.Arrays.stream(input.split("[_\\-]"))
                .map(word -> word.isEmpty() ? word : word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(java.util.stream.Collectors.joining());
    }

    public String getClassNameStr(String docTypeModel) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return Class.forName("com.lumen.api.v1.models.documents." + docTypeModel)
                .getDeclaredConstructor()
                .newInstance()
                .toString()
                .replaceAll("null", "-");
    }
}
