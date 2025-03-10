package org.smart.model.response;


/**
 * 反馈响应模型
 */
public class FeedbackResponse {
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
        return "FeedbackResponse{" +
                "result='" + result + '\'' +
                '}';
    }
}