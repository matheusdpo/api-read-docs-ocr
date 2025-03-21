package com.lumen.api.v1.services;

import com.lumen.api.v1.utils.HttpUnirestUtils;
import com.lumen.api.v1.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @Value("${gemini.api.prompt.read-docs}")
    private String geminiApiReadDocsPrompt;

    @Value("${gemini.api.prompt.check-signature}")
    private String geminiApiCheckSignaturePrompt;

    @Value("${gemini.api.v1.package}")
    private String geminiApiPackage;

    @Autowired
    private HttpUnirestUtils httpUnirestUtils;

    @Autowired
    private JsonUtils jsonUtils;

    public String getResponseBodyStr(String base64, String prompt, String mimeType) throws Exception {
        String body = jsonUtils.jsonGeminiBuilder(base64, prompt, mimeType);
        return httpUnirestUtils.post(geminiApiUrl, geminiApiKey, body).getBody();
    }

    public String getGeminiApiPackage() {
        return this.geminiApiPackage;
    }

    public String getGeminiApiReadDocsPrompt() {
        return this.geminiApiReadDocsPrompt;
    }

    public String getGeminiApiCheckSignaturePrompt() {
        return this.geminiApiCheckSignaturePrompt;
    }
}