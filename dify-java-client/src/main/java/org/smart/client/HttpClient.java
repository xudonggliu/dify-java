package org.smart.client;

import org.smart.config.DifyConfig;
import org.smart.exception.SDKException;
import org.smart.utils.JSONUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * HTTP 客户端类
 */
public class HttpClient {
    private DifyConfig config;

    public HttpClient(DifyConfig config) {
        this.config = config;
    }

    /**
     * 发送 GET 请求
     *
     * @param endpoint API 路径
     * @param params   请求参数
     * @return 响应结果
     * @throws SDKException 请求异常
     */
    public String sendGetRequest(String endpoint, Map<String, String> params) throws SDKException {
        try {
            StringBuilder urlBuilder = new StringBuilder(config.getApiUrl() + endpoint);
            if (params != null && !params.isEmpty()) {
                urlBuilder.append("?");
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
                urlBuilder.deleteCharAt(urlBuilder.length() - 1);
            }
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // 设置超时时间
            conn.setConnectTimeout(config.getConnectionTimeout() * 1000);
            conn.setReadTimeout(config.getReadTimeout() * 1000);
            for (Map.Entry<String, String> entry : config.buildHeaders().entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                throw new SDKException("GET request failed with status code: " + responseCode);
            }
        } catch (IOException e) {
            throw new SDKException("Failed to send GET request", e);
        }
    }

    /**
     * 发送 POST 请求
     *
     * @param endpoint API 路径
     * @param params   请求参数
     * @return 响应结果
     * @throws SDKException 请求异常
     */
    public String sendPostRequest(String endpoint, Map<String, Object> params) throws SDKException {
        try {
            URL url = new URL(config.getApiUrl() + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            // 设置超时时间
            conn.setConnectTimeout(config.getConnectionTimeout() * 1000);
            conn.setReadTimeout(config.getReadTimeout() * 1000);
            conn.setDoOutput(true);
            for (Map.Entry<String, String> entry : config.buildHeaders().entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            if (params != null && !params.isEmpty()) {
                String data = JSONUtil.toJSONString(params);
                conn.getOutputStream().write(data.getBytes());
            }
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                throw new SDKException("POST request failed with status code: " + responseCode);
            }
        } catch (IOException e) {
            throw new SDKException("Failed to send POST request", e);
        }
    }

    /**
     * 发送 DELETE 请求
     *
     * @param endpoint API 路径
     * @param params   请求参数
     * @return 响应结果
     * @throws SDKException 请求异常
     */
    public String sendDeleteRequest(String endpoint, Map<String, String> params) throws SDKException {
        try {
            URL url = new URL(config.getApiUrl() + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            // 设置超时时间
            conn.setConnectTimeout(config.getConnectionTimeout() * 1000);
            conn.setReadTimeout(config.getReadTimeout() * 1000);
            conn.setDoOutput(true);
            for (Map.Entry<String, String> entry : config.buildHeaders().entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            if (params != null && !params.isEmpty()) {
                String data = JSONUtil.toJSONString(params);
                conn.getOutputStream().write(data.getBytes());
            }
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                throw new SDKException("POST request failed with status code: " + responseCode);
            }
        } catch (IOException e) {
            throw new SDKException("Failed to send POST request", e);
        }
    }
}