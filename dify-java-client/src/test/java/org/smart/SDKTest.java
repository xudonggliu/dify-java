package org.smart;


import org.junit.jupiter.api.Test;
import org.smart.client.DifyAPIClient;
import org.smart.config.DifyConfig;
import org.smart.exception.SDKException;
import org.smart.model.response.AppInfoResponse;
import org.smart.model.response.ChatCompletionResponse;
import org.smart.model.response.StopResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SDKTest {
    // 初始化配置
    DifyConfig config = new DifyConfig("app-wE9eOQoe9Lqeg36SXHVkthN1", "https://api.dify.ai/v1");
    // 初始化 API 客户端
    DifyAPIClient client = new DifyAPIClient(config);

    @Test
    public void test() {
        // 发送对话消息 阻塞模式
        sendChatMessage();
    }
    private void sendChatMessage(){
        // 初始化 API 客户端
        DifyAPIClient client = new DifyAPIClient(config);

        try {
            // 示例：发送对话消息 阻塞模式
            Map<String, Object> inputs = new HashMap<>();
            //  inputs.put("key", "value");
            ChatCompletionResponse chatResponse = client.sendChatMessage(
                    "如何查看天气",
                    inputs,
                    "blocking",
                    "user123",
                    null,
                    new ArrayList<>(),
                    true
            );
            System.out.println("Chat Response: " + chatResponse.getAnswer());

            // 示例：获取应用基本信息
            AppInfoResponse appInfo = client.getAppInfo();
            System.out.println("AppInfoResponse: " + appInfo.toString());
            System.out.println("App Name: " + appInfo.getName());

            // 其他 API 调用示例...
        } catch (SDKException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
//
    }
}