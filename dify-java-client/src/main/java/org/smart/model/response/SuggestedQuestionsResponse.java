package org.smart.model.response;


import java.util.List;

/**
 * 建议问题列表响应模型
 */
public class SuggestedQuestionsResponse {
    private String result;
    private List<String> data;

    // Getters and setters
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SuggestedQuestionsResponse{" +
                "result='" + result + '\'' +
                ", data=" + data +
                '}';
    }
}