package com.lumen.api.v1.services;

import com.lumen.api.v1.exceptions.OcrSpaceException;
import com.lumen.api.v1.models.responses.ocrspace.ResponseOcrSpaceModel;
import com.lumen.api.v1.utils.LogUtils;
import com.lumen.api.v1.utils.SerializationUtils;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Serviço para processamento de imagens utilizando a API OCR Space
 */
@Service
public class OcrSpaceService {

    /**
     * URL da API OCR Space
     */
    @Value("${ocr.space.api.url}")
    private String url;

    /**
     * API Key da OCR Space
     */
    @Value("${ocr.space.api.key}")
    private String apiKey;

    @Autowired
    private LogUtils logger;

    @Autowired
    private SerializationUtils serializationUtils;


    public String getString(String base64) throws OcrSpaceException {
        try {
            HttpResponse<String> response = null;

            for (int i = 0; i < 5; i++) {
                try {
                    response = Unirest.post(url)
                            .field("base64Image", base64)
                            .field("OCREngine", "2")
                            .field("apikey", apiKey)
                            .asString();

                    ResponseOcrSpaceModel responseOcrSpaceModel = serializationUtils.fromJson2Class(response.getBody(), ResponseOcrSpaceModel.class);

                    if (responseOcrSpaceModel.isErroredOnProcessing()) {
                        logger.error("Erro ao processar imagem, OCRExitCode: " + responseOcrSpaceModel.getOcrExitCode());
                        return "";
                    }

                    return Optional.ofNullable(responseOcrSpaceModel.getParsedResults())
                            .filter(list -> !list.isEmpty())
                            .map(list -> list.get(0).getParsedText())
                            .orElse("");
                } catch (Exception e) {
                    logger.error("Erro ao processar resposta da API OCR Space: " + e.getMessage());
                    logger.error("Verificar status do OCR Space em: https://status.ocr.space/");
                    logger.error("Aguardando 5 minuto para nova tentativa");
                }
            }

            throw new OcrSpaceException("Erro ao processar imagem");
        } catch (Exception e) {
            logger.error("Erro ao processar imagem: " + e.getMessage());
            throw new OcrSpaceException("Erro ao processar imagem");
        }
    }
}