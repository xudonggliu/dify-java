# Dify Java SDK
This is the Java SDK for the Dify API, which allows you to seamlessly integrate Dify into your Java applications.

## Installation

Please ensure that you have included the necessary dependencies in your project, such as `fastjson`, `okhttp3`, etc. You can use Maven, Gradle, or any other dependency management tool of your choice. The SDK itself can be included once it is published to a package repository.

For the sake of this README, let's assume the SDK is available on Maven Central:

```xml
<dependency>
    <groupId>smart.ai.dify</groupId>
    <artifactId>dify-java-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage
Once the SDK is installed, you can use it in your project as follows:

```java
public class Main {
    public static void main(String[] args) {
        // 初始化配置
        DifyConfig config = new DifyConfig("your_chat_api_key", "https://api.dify.ai/v1");

        // 初始化 API 客户端
        DifyAPIClient client = new DifyAPIClient(config);

        try {
            sendChatMessage(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 发送对话消息（阻塞模式）
     */
    private static void sendChatMessage(DifyAPIClient client){

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
            System.out.println("Chat Response: " + chatResponse.toString());

            // 示例：获取应用基本信息
            AppInfoResponse appInfo = client.getAppInfo();
            System.out.println("AppInfoResponse: " + appInfo.toString());
            System.out.println("App Name: " + appInfo.getName());

            // 其他 API 调用示例...
        } catch (SDKException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送对话消息（流式模式）
     */
    private static void sendChatMessageStreaming(DifyAPIClient client){

        try {
            // 示例：发送对话消息 阻塞模式
            Map<String, Object> inputs = new HashMap<>();
            //  inputs.put("key", "value");
            DifyAPIClient.Stream<ChatCompletionResponse> stream = client.sendChatMessageStreaming(
                    "如何查看天气",
                    inputs,
                    "user123",
                    null,
                    new ArrayList<>(),
                    true
            );

            for (ChatCompletionResponse response : stream) {
                System.out.println("Stream Response: " + response.getAnswer());
            }
//            System.out.println("Chat Response: " + chatResponse.getAnswer());

            // 示例：获取应用基本信息
            AppInfoResponse appInfo = client.getAppInfo();
            System.out.println("AppInfoResponse: " + appInfo.toString());
            System.out.println("App Name: " + appInfo.getName());

            // 其他 API 调用示例...
        } catch (SDKException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止响应(仅支持流式模式)
     *  task_id (string) 任务 ID，可在流式返回 Chunk 中获取
     *  user (string) Required 用户标识，用于定义终端用户的身份，必须和发送消息接口传入 user 保持一致
     *  result (string) 固定返回 success
     *
     */
    private static void stopResponse(DifyAPIClient client){

        try {
            StopResponse chatResponse = client.stopResponse(
                    "d5b81252-a1b3-4a1a-b41a-c64d6914a361",
                    "user123"
            );
            System.out.println("Chat Response: " + chatResponse.getResult());

            // 示例：获取应用基本信息
            AppInfoResponse appInfo = client.getAppInfo();
            System.out.println("AppInfoResponse: " + appInfo.toString());
            System.out.println("App Name: " + appInfo.getName());

            // 其他 API 调用示例...
        } catch (SDKException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息反馈
     */
    private static void sendFeedback(DifyAPIClient client){

        try {
            FeedbackResponse chatResponse = client.sendFeedback(
                    "0892e93-efee-4b7d-815b-8991de71eeea",
                    "like",
                    "user123",
                    "<details style=\\\"color:gray;background-color: #f8f8f8;padding: 8px;border-radius: 4px;\\\" open> <summary> Thinking... </summary>好，用户问如何查看天气。我先看看知识库里的内容。知识库提到了手机天气APP可以查看对应的天气情况，还有可能其他地方也有天气信息。工作台部分显示了近四天的天气，可能在那里也能看。所以，我应该告诉用户可以通过天气APP或者在工作台查看。这样回答应该能满足用户的需求。\\n</details>\\n\\n要查看天气，您可以通过手机上的天气APP查看对应天气情况。此外，工作台顶部也展示了近四天的天气情况供您参考。"
            );
            System.out.println("Chat Response: " + chatResponse.toString());

            // 示例：获取应用基本信息
            AppInfoResponse appInfo = client.getAppInfo();
            System.out.println("AppInfoResponse: " + appInfo.toString());
            System.out.println("App Name: " + appInfo.getName());

            // 其他 API 调用示例...
        } catch (SDKException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取下一步提问建议
     */
    private static void getSuggestedQuestions(DifyAPIClient client){

        try {
            SuggestedQuestionsResponse chatResponse = client.getSuggestedQuestions(
                    "00892e93-efee-4b7d-815b-8991de71eeea",
                    "user123"
            );
            System.out.println("Chat Response: " + chatResponse.toString());

            // 示例：获取应用基本信息
            AppInfoResponse appInfo = client.getAppInfo();
            System.out.println("AppInfoResponse: " + appInfo.toString());
            System.out.println("App Name: " + appInfo.getName());

            // 其他 API 调用示例...
        } catch (SDKException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取会话历史消息
     */
    private static void getMessagesHistory(DifyAPIClient client){

        try {
            MessagesHistoryResponse chatResponse = client.getMessagesHistory(
                    "97f8c679-9228-4003-9006-9950a7c9b838",
                    "user123",
                    "",
                    20
            );
            System.out.println("Chat Response: " + chatResponse.toString());

            // 示例：获取应用基本信息
            AppInfoResponse appInfo = client.getAppInfo();
            System.out.println("AppInfoResponse: " + appInfo.toString());
            System.out.println("App Name: " + appInfo.getName());

            // 其他 API 调用示例...
        } catch (SDKException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取会话列表
     */
    private static void getConversationsList(DifyAPIClient client){

        try {
            ConversationsListResponse chatResponse = client.getConversationsList(
                    "user123",
                    "",
                    20,
                    "created_at"
            );
            System.out.println("Chat Response: " + chatResponse.toString());

            // 示例：获取应用基本信息
            AppInfoResponse appInfo = client.getAppInfo();
            System.out.println("AppInfoResponse: " + appInfo.toString());
            System.out.println("App Name: " + appInfo.getName());

            // 其他 API 调用示例...
        } catch (SDKException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除会话
     */
    private static void deleteConversation(DifyAPIClient client){

        try {
            DeleteConversationResponse chatResponse = client.deleteConversation(
                    "dd669738-d579-42a7-aa41-f9811750378b",
                    "user123"
            );
            System.out.println("Chat Response: " + chatResponse.toString());

            // 示例：获取应用基本信息
            AppInfoResponse appInfo = client.getAppInfo();
            System.out.println("AppInfoResponse: " + appInfo.toString());
            System.out.println("App Name: " + appInfo.getName());

            // 其他 API 调用示例...
        } catch (SDKException e) {
            e.printStackTrace();
        }
    }

    /**
     * 会话重命名
     */
    private static void renameConversation(DifyAPIClient client){

        try {
            RenameConversationResponse chatResponse = client.renameConversation(
                    "ef5caaf0-3355-4adc-b433-082053a05b1c",
                    "重命名的会话",
                    false,
                    "user123"
            );
            System.out.println("Chat Response: " + chatResponse.toString());

            // 示例：获取应用基本信息
            AppInfoResponse appInfo = client.getAppInfo();
            System.out.println("AppInfoResponse: " + appInfo.toString());
            System.out.println("App Name: " + appInfo.getName());
            AppParametersResponse appParameters = client.getAppParameters();
            System.out.println("AppParametersResponse: " + appParameters.toString());
            AppMetaResponse appMeta = client.getAppMeta();
            System.out.println("AppMetaResponse: " + appMeta.toString());

            // 其他 API 调用示例...
        } catch (SDKException e) {
            e.printStackTrace();
        }
    }
}
```

Replace `'your-api-key-here'` with your actual Dify API key.

## License
This SDK is released under the MIT License.
