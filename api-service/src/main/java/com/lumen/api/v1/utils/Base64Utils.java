package com.lumen.api.v1.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

@Component
public class Base64Utils {

    @Autowired
    private LogUtils logger;

    @Autowired
    private FileUtils fileUtils;

    public String encode(MultipartFile path) throws IOException {

        File file = fileUtils.convert(path);

        String extension = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1);

        String prefixBase64 = switch (extension) {
            case "png" -> "data:image/png;base64,";
            case "jpg" -> "data:image/jpg;base64,";
            case "jpeg" -> "data:image/jpg;base64,";
            case "pdf" -> "data:application/pdf;base64,";
            default -> throw new IllegalStateException("Unexpected value: " + path);
        };


        try (FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath())) {
            byte[] bytes = fileInputStream.readAllBytes();
            return prefixBase64 + Base64.getEncoder().encodeToString(bytes);
        } catch (FileNotFoundException e) {
            logger.error("Arquivo não encontrado", e);
            return "";
        } catch (IOException e) {
            logger.error("Erro ao ler arquivo", e);
            return "";
        }
    }
}
