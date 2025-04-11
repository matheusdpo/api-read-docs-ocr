package com.lumen.commons.utils;

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

        try (FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath())) {
            byte[] bytes = fileInputStream.readAllBytes();
            return  Base64.getEncoder().encodeToString(bytes);
        } catch (FileNotFoundException e) {
            logger.error("Arquivo não encontrado", e);
            return "";
        } catch (IOException e) {
            logger.error("Erro ao ler arquivo", e);
            return "";
        }
    }

    public byte[] decode(String base64Data) {
        return Base64.getDecoder().decode(base64Data);
    }
}
