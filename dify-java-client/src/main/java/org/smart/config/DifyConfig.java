package org.smart.config;

import java.util.HashMap;
import java.util.Map;

/**
 * SDK 配置类
 */
public class DifyConfig {
    private String apiKey;
    private String apiUrl;

    // 超时时间，单位为秒
    private int connectionTimeout;

    // 超时时间，单位为秒
    private int readTimeout;

    public DifyConfig(String apiKey,  String apiUrl) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        // 默认值设置
        this.connectionTimeout = 100; // 100 秒
        this.readTimeout = 100;       // 100 秒
    }

    public DifyConfig(String apiKey,  String apiUrl, int connectionTimeout, int readTimeout) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        // 默认值设置
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
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