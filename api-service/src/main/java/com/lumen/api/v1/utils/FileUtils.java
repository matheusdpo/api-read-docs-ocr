package com.lumen.api.v1.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Component
public class FileUtils {

    public File convert(MultipartFile multipartFile) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();
        String fileExtension = "";

        if (Objects.nonNull(originalFileName) && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        File file = File.createTempFile("temp", fileExtension);

        multipartFile.transferTo(file);

        return file;
    }
}
