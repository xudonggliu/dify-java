package org.smart.config;

import java.util.HashMap;
import java.util.Map;

/**
 * SDK 配置类
 */
public class DifyConfig {
    private String apiKey;
    private String apiUrl;

    public DifyConfig(String apiKey,  String apiUrl) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * 构建请求头
     *
     * @return 请求头
     */
    public Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + apiKey);
        headers.put("Content-Type", "application/json");
        return headers;
    }
}