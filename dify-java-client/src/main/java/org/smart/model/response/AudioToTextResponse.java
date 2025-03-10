package org.smart.model.response;


/**
 * 语音转文字响应模型
 */
public class AudioToTextResponse {
    private String text;

    // Getters and setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "AudioToTextResponse{" +
                "text='" + text + '\'' +
                '}';
    }
}