package org.smart.model.response;

import org.smart.model.response.entity.Usage;

import java.util.List;
import java.util.Map;

/**
 * 对话完成响应模型
 */
/**
 * 对话完成响应模型
 */
public class ChatCompletionResponse {
    private String event;

    private String taskId;

    private String id;

    private String messageId;
    private String conversationId;
    private String mode;
    private String answer;
    private Map<String, Object> metadata;
//    private Usage usage;
//    private List<RetrieverResource> retrieverResources;
    private int createdAt;

    // Getters and setters


    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ChatCompletionResponse{" +
                "event='" + event + '\'' +
                ", taskId='" + taskId + '\'' +
                ", id='" + id + '\'' +
                ", messageId='" + messageId + '\'' +
                ", conversationId='" + conversationId + '\'' +
                ", mode='" + mode + '\'' +
                ", answer='" + answer + '\'' +
                ", metadata=" + metadata +
                ", createdAt=" + createdAt +
                '}';
    }
}