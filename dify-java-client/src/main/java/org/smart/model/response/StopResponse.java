package org.smart.model.response;


/**
 * 停止响应模型
 */
public class StopResponse {
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
        return "StopResponse{" +
                "result='" + result + '\'' +
                '}';
    }
}