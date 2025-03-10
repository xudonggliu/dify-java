package org.smart.model.response.entity;

import java.util.List;
import java.util.Map;

public class HistoryMessageFile {

    private String id;

    private String conversationId;

    private Map<String, Object> inputs ;

    private String query;

    private List<MessageFileVo> messageFiles;

    private String answer;


    private Long created_at;

    // 反馈信息
    //rating (string) 点赞 like / 点踩 dislike
    private Map<String, Object> feedback;

    // (array[RetrieverResource]) 引用和归属分段列表
    private List<Map<String, Object>> retrieverResources;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public Map<String, Object> getInputs() {
        return inputs;
    }

    public void setInputs(Map<String, Object> inputs) {
        this.inputs = inputs;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public Map<String, Object> getFeedback() {
        return feedback;
    }

    public void setFeedback(Map<String, Object> feedback) {
        this.feedback = feedback;
    }

    public List<Map<String, Object>> getRetrieverResources() {
        return retrieverResources;
    }

    public void setRetrieverResources(List<Map<String, Object>> retrieverResources) {
        this.retrieverResources = retrieverResources;
    }

    public List<MessageFileVo> getMessageFiles() {
        return messageFiles;
    }

    public void setMessageFiles(List<MessageFileVo> messageFiles) {
        this.messageFiles = messageFiles;
    }
}
