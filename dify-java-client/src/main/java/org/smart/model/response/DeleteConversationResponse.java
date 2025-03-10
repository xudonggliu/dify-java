package org.smart.model.response;


/**
 * 删除会话响应模型
 */
public class DeleteConversationResponse {
    private String result;

    // Getters and setters
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "DeleteConversationResponse{" +
                "result='" + result + '\'' +
                '}';
    }
}