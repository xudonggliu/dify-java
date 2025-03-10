package org.smart.client;

import org.smart.config.DifyConfig;
import org.smart.exception.SDKException;
import org.smart.model.response.*;
import org.smart.model.response.entity.MessageFile;
import org.smart.utils.JSONUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * API 客户端类
 */
public class DifyAPIClient {
    private HttpClient httpClient;

    private DifyConfig config;


    public DifyAPIClient(DifyConfig config) {
        this.httpClient = new HttpClient(config);
        this.config = config;
    }

    /**
     * 发送对话消息（阻塞模式）
     *
     * @param query          用户输入/提问内容
     * @param inputs         输入参数 允许传入 App 定义的各变量值。 inputs 参数包含了多组键值对（Key/Value pairs），每组的键对应一个特定变量，每组的值则是该变量的具体值。 如果变量是文件类型，请指定一个包含以下 files 中所述键的对象。 默认 {}
     * @param responseMode   响应模式 streaming 流式模式（推荐）。基于 SSE（Server-Sent Events）实现类似打字机输出方式的流式返回。
     * blocking 阻塞模式，等待执行完毕后返回结果。（请求若流程较长可能会被中断）。 由于 Cloudflare 限制，请求会在 100 秒超时无返回后中断。
     * @param user           用户标识 用户标识，用于定义终端用户的身份，方便检索、统计。 由开发者定义规则，需保证用户标识在应用内唯一。
     * @param conversationId 会话 ID （选填）会话 ID，需要基于之前的聊天记录继续对话，必须传之前消息的 conversation_id。
     * @param files          文件列表 文件列表，适用于传入文件结合文本理解并回答问题，仅当模型支持 Vision 能力时可用。
     *
     * type (string) 支持类型：
     * document 具体类型包含：'TXT', 'MD', 'MARKDOWN', 'PDF', 'HTML', 'XLSX', 'XLS', 'DOCX', 'CSV', 'EML', 'MSG', 'PPTX', 'PPT', 'XML', 'EPUB'
     * image 具体类型包含：'JPG', 'JPEG', 'PNG', 'GIF', 'WEBP', 'SVG'
     * audio 具体类型包含：'MP3', 'M4A', 'WAV', 'WEBM', 'AMR'
     * video 具体类型包含：'MP4', 'MOV', 'MPEG', 'MPGA'
     * custom 具体类型包含：其他文件类型
     * transfer_method (string) 传递方式:
     * remote_url: 图片地址。
     * local_file: 上传文件。
     * url 图片地址。（仅当传递方式为 remote_url 时）。
     * upload_file_id 上传文件 ID。（仅当传递方式为 local_file 时）
     * @Param autoGenerateName （选填）自动生成标题，默认 true。 若设置为 false，则可通过调用会话重命名接口并设置 auto_generate 为 true 实现异步生成标题。
     * @return 响应结果
     * @throws SDKException 请求异常
     */
    public ChatCompletionResponse sendChatMessage(String query, Map<String, Object> inputs, String responseMode,
                                                  String user, String conversationId, List<MessageFile> files,boolean autoGenerateName) throws SDKException {
        Map<String, Object> params = new HashMap<>();
        params.put("query", query);
        params.put("inputs", inputs);
        params.put("response_mode", responseMode);
        params.put("user", user);
        params.put("conversation_id", conversationId);
        params.put("files", files);
        if(!autoGenerateName){
            params.put("auto_generate_name", false);
        }
        String response = httpClient.sendPostRequest("/chat-messages", params);
        return JSONUtil.parseJSON(response, ChatCompletionResponse.class);
    }

    /**
     * 发送对话消息（流式模式）
     *
     * @param query          用户输入/提问内容
     * @param inputs         输入参数
     * @param user           用户标识
     * @param conversationId 会话 ID
     * @param files          文件列表
     * @param autoGenerateName 自动生成标题
     * @return 流式模式的响应块迭代器
     * @throws SDKException 请求异常
     */
    public Stream<ChatCompletionResponse> sendChatMessageStreaming(
            String query,
            Map<String, Object> inputs,
            String user,
            String conversationId,
            List<MessageFile> files,
            boolean autoGenerateName) throws SDKException {
        Map<String, Object> params = new HashMap<>();
        params.put("query", query);
        params.put("inputs", inputs);
        params.put("response_mode", "streaming");
        params.put("user", user);
        params.put("conversation_id", conversationId);
        params.put("files", files);
        if(!autoGenerateName){
            params.put("auto_generate_name", false);
        }


        HttpURLConnection conn = null;
        try {
            // 发送请求
            conn = (HttpURLConnection) new URL(config.getApiUrl() + "/chat-messages").openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            for (Map.Entry<String, String> entry : config.buildHeaders().entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }

            // 写入请求体
            String data = JSONUtil.toJSONString(params);
            conn.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));
            conn.getOutputStream().flush();

            // 检查响应码
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new SDKException("POST request failed with status code: " + responseCode);
            }

            // 读取流式响应
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            Stream<ChatCompletionResponse> stream = new Stream<>();
            StringBuilder currentChunk = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                // 处理每个行，累加到当前块
                currentChunk.append(line);

                // 检查是否是完整块（以 `\n\n` 结尾）
                if (line.isEmpty() && currentChunk.length() > 0) {
                    String chunk = currentChunk.toString();
                    currentChunk.setLength(0); // 重置当前块

                    // 处理完整块
                    if (chunk.startsWith("data: ")) {
                        String json = chunk.substring("data: ".length()).trim();
                        if (!json.isEmpty()) {
                            ChatCompletionResponse response = JSONUtil.parseJSON(json, ChatCompletionResponse.class);
                            System.out.println("Task ID: " + response.getTaskId());
                            stream.add(response);
                        }
                    }
                }
            }

            in.close();
            conn.disconnect();
            return stream;

        } catch (IOException e) {
            throw new SDKException("Failed to send POST request", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    // 流式模式示例
    /*Stream<ChatCompletionResponse> stream = client.sendChatMessageStreaming(
            "Hello, world!",
            inputs,
            "user123",
            null,
            null
    );

    for (ChatCompletionResponse response : stream) {
        System.out.println("Stream Response: " + response.getAnswer());
    }*/

    /**
     * 简单的流式块迭代器
     */
    public static class Stream<T> implements Iterable<T> {
        private List<T> items = new ArrayList<>();

        public void add(T item) {
            items.add(item);
        }

        @Override
        public Iterator<T> iterator() {
            return items.iterator();
        }
    }

    /**
     * 上传文件 暂未测试
     *
     * @param file 文件 要上传的文件。
     * @param user 用户标识 用户标识，用于定义终端用户的身份，必须和发送消息接口传入 user 保持一致。
     * @return 文件上传响应
     * Response
     * 成功上传后，服务器会返回文件的 ID 和相关信息。
     *
     * id (uuid) ID
     * name (string) 文件名
     * size (int) 文件大小（byte）
     * extension (string) 文件后缀
     * mime_type (string) 文件 mime-type
     * created_by (uuid) 上传人 ID
     * created_at (timestamp) 上传时间
     * Errors
     * 400，no_file_uploaded，必须提供文件
     * 400，too_many_files，目前只接受一个文件
     * 400，unsupported_preview，该文件不支持预览
     * 400，unsupported_estimate，该文件不支持估算
     * 413，file_too_large，文件太大
     * 415，unsupported_file_type，不支持的扩展名，当前只接受文档类文件
     * 503，s3_connection_failed，无法连接到 S3 服务
     * 503，s3_permission_denied，无权限上传文件到 S3
     * 503，s3_file_too_large，文件超出 S3 大小限制
     * @throws SDKException 请求异常
     */
    public FileUploadResponse uploadFile(File file, String user) throws SDKException {
        // 实际实现中需要处理文件上传的逻辑
        return null;
    }

    /**
     * 停止响应
     *
     * @param taskId 任务 ID (string) 任务 ID，可在流式返回 Chunk 中获取
     * @param user   用户标识 (string) Required 用户标识，用于定义终端用户的身份，必须和发送消息接口传入 user 保持一致。
     * @return 响应结果 result (string) 固定返回 success
     * @throws SDKException 请求异常
     */
    public StopResponse stopResponse(String taskId, String user) throws SDKException {
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        String response = httpClient.sendPostRequest("/chat-messages/" + taskId + "/stop", params);
        return JSONUtil.parseJSON(response, StopResponse.class);
    }

    /**
     * 消息反馈 消息终端用户反馈、点赞，方便应用开发者优化输出预期。
     *
     * @param messageId 消息 ID
     * @param rating    点赞/点踩 点赞 like, 点踩 dislike, 撤销点赞 null
     * @param user      用户标识 用户标识，由开发者定义规则，需保证用户标识在应用内唯一。
     * @param content   反馈信息 消息反馈的具体信息。
     * @return 响应结果  (string) 固定返回 success
     * @throws SDKException 请求异常
     */
    public FeedbackResponse sendFeedback(String messageId, String rating, String user, String content) throws SDKException {
        Map<String, Object> params = new HashMap<>();
        params.put("rating", rating);
        params.put("user", user);
        params.put("content", content);
        String response = httpClient.sendPostRequest("/messages/" + messageId + "/feedbacks", params);
        return JSONUtil.parseJSON(response, FeedbackResponse.class);
    }

    /**
     * 获取下一轮建议问题列表
     *
     * @param messageId 消息 ID
     * @param user      用户标识 用户标识，由开发者定义规则，需保证用户标识在应用内唯一。
     * @return 建议问题列表响应
     * @throws SDKException 请求异常
     */
    public SuggestedQuestionsResponse getSuggestedQuestions(String messageId, String user) throws SDKException {
        Map<String, String> params = new HashMap<>();
        params.put("user", user);
        String response = httpClient.sendGetRequest("/messages/" + messageId + "/suggested", params);
        return JSONUtil.parseJSON(response, SuggestedQuestionsResponse.class);
    }

    /**
     * 获取会话历史消息 滚动加载形式返回历史聊天记录，第一页返回最新 limit 条，即：倒序返回。
     *
     * @param conversationId 会话 ID
     * @param user           用户标识 用户标识，由开发者定义规则，需保证用户标识在应用内唯一。
     * @param firstId        第一条记录的 ID 当前页第一条聊天记录的 ID，默认 null
     * @param limit          返回条数 一次请求返回多少条聊天记录，默认 20 条。
     * @return 历史消息响应
     * Response
     * data (array[object]) 消息列表
     * id (string) 消息 ID
     * conversation_id (string) 会话 ID
     * inputs (object) 用户输入参数。
     * query (string) 用户输入 / 提问内容。
     * message_files (array[object]) 消息文件
     * id (string) ID
     * type (string) 文件类型，image 图片
     * url (string) 预览图片地址
     * belongs_to (string) 文件归属方，user 或 assistant
     * answer (string) 回答消息内容
     * created_at (timestamp) 创建时间
     * feedback (object) 反馈信息
     * rating (string) 点赞 like / 点踩 dislike
     * retriever_resources (array[RetrieverResource]) 引用和归属分段列表
     * has_more (bool) 是否存在下一页
     * limit (int) 返回条数，若传入超过系统限制，返回系统限制数量
     * @throws SDKException 请求异常
     */
    public MessagesHistoryResponse getMessagesHistory(String conversationId, String user, String firstId, int limit) throws SDKException {
        Map<String, String> params = new HashMap<>();
        params.put("conversation_id", conversationId);
        params.put("user", user);
        params.put("first_id", firstId);
        params.put("limit", String.valueOf(limit));
        String response = httpClient.sendGetRequest("/messages", params);
        return JSONUtil.parseJSON(response, MessagesHistoryResponse.class);
    }

    /**
     * 获取会话列表 获取当前用户的会话列表，默认返回最近的 20 条。
     *
     * @param user    用户标识 用户标识，由开发者定义规则，需保证用户标识在应用内唯一。
     * @param lastId  最后一条记录的 ID （选填）当前页最后面一条记录的 ID，默认 null
     * @param limit   返回条数 （选填）一次请求返回多少条记录，默认 20 条，最大 100 条，最小 1 条。
     * @param sortBy  排序字段 （选填）排序字段，默认 -updated_at(按更新时间倒序排列)
     * 可选值：created_at, -created_at, updated_at, -updated_at
     * 字段前面的符号代表顺序或倒序，-代表倒序
     * @return 会话列表响应
     * @throws SDKException 请求异常
     */
    public ConversationsListResponse getConversationsList(String user, String lastId, int limit, String sortBy) throws SDKException {
        Map<String, String> params = new HashMap<>();
        params.put("user", user);
        params.put("last_id", lastId);
        params.put("limit", String.valueOf(limit));
        params.put("sort_by", sortBy);
        String response = httpClient.sendGetRequest("/conversations", params);
        return JSONUtil.parseJSON(response, ConversationsListResponse.class);
    }

    /**
     * 删除会话
     *
     * @param conversationId 会话 ID
     * @param user           用户标识 用户标识，由开发者定义规则，需保证用户标识在应用内唯一。
     * @return 响应结果 result (string) 固定返回 success
     * @throws SDKException 请求异常
     */
    public DeleteConversationResponse deleteConversation(String conversationId, String user) throws SDKException {
        Map<String, String> params = new HashMap<>();
        params.put("user", user);
        String response = httpClient.sendDeleteRequest("/conversations/" + conversationId, params);
        return JSONUtil.parseJSON(response, DeleteConversationResponse.class);
    }

    /**
     * 会话重命名 对会话进行重命名，会话名称用于显示在支持多会话的客户端上。
     *
     * @param conversationId 会话 ID conversation_id (string) 会话 ID
     * @param name           新名称 （选填）名称，若 auto_generate 为 true 时，该参数可不传。
     * @param autoGenerate   是否自动生成标题 （选填）自动生成标题，默认 false。
     * @param user           用户标识 用户标识，由开发者定义规则，需保证用户标识在应用内唯一。
     * @return 响应结果
     * @throws SDKException 请求异常
     */
    public RenameConversationResponse renameConversation(String conversationId, String name, boolean autoGenerate, String user) throws SDKException {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("auto_generate", autoGenerate);
        params.put("user", user);
        String response = httpClient.sendPostRequest("/conversations/" + conversationId + "/name", params);
        return JSONUtil.parseJSON(response, RenameConversationResponse.class);
    }

    /**
     * 语音转文字 暂未测试
     * 该接口需使用 multipart/form-data 进行请求。
     * @param file 文件 语音文件。 支持格式：['mp3', 'mp4', 'mpeg', 'mpga', 'm4a', 'wav', 'webm'] 文件大小限制：15MB
     * @param user 用户标识 用户标识，由开发者定义规则，需保证用户标识在应用内唯一。
     * @return 语音转文字响应 text (string) 输出文字
     * @throws SDKException 请求异常
     */
    public AudioToTextResponse audioToText(File file, String user) throws SDKException {
        // 实际实现中需要处理文件上传的逻辑
        return null;
    }

    /**
     * 文字转语音 暂未测试
     * {
     *   "Content-Type": "audio/wav"
     * }
     *
     * @param messageId 消息 ID Dify 生成的文本消息，那么直接传递生成的message-id 即可，后台会通过 message_id 查找相应的内容直接合成语音信息。如果同时传 message_id 和 text，优先使用 message_id。
     * @param text      文字内容 语音生成内容。如果没有传 message-id的话，则会使用这个字段的内容
     * @param user      用户标识 用户标识，由开发者定义规则，需保证用户标识在应用内唯一。
     * @return 文字转语音响应
     * @throws SDKException 请求异常
     */
    public TextToAudioResponse textToAudio(String messageId, String text, String user) throws SDKException {
        Map<String, Object> params = new HashMap<>();
        params.put("message_id", messageId);
        params.put("text", text);
        params.put("user", user);
        String response = httpClient.sendPostRequest("/text-to-audio", params);
        return JSONUtil.parseJSON(response, TextToAudioResponse.class);
    }

    /**
     * 获取应用基本信息
     *
     * @return 应用基本信息响应
     * @throws SDKException 请求异常
     */
    public AppInfoResponse getAppInfo() throws SDKException {
        String response = httpClient.sendGetRequest("/info", null);
        return JSONUtil.parseJSON(response, AppInfoResponse.class);
    }

    /**
     * 获取应用参数
     *
     * @return 应用参数响应
     * @throws SDKException 请求异常
     */
    public AppParametersResponse getAppParameters() throws SDKException {
        String response = httpClient.sendGetRequest("/parameters", null);
        return JSONUtil.parseJSON(response, AppParametersResponse.class);
    }

    /**
     * 获取应用 Meta 信息
     *
     * @return 应用 Meta 信息响应
     * @throws SDKException 请求异常
     */
    public AppMetaResponse getAppMeta() throws SDKException {
        String response = httpClient.sendGetRequest("/meta", null);
        return JSONUtil.parseJSON(response, AppMetaResponse.class);
    }
}