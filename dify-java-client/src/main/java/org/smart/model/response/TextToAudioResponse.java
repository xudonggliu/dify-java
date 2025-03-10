package org.smart.model.response;


import java.util.Arrays;

/**
 * 文字转语音响应模型
 */
public class TextToAudioResponse {
    // 实际响应可能包含音频流，此处仅提供简单示例
    private byte[] audioData;

    // Getters and setters
    public byte[] getAudioData() {
        return audioData;
    }

    public void setAudioData(byte[] audioData) {
        this.audioData = audioData;
    }

    @Override
    public String toString() {
        return "TextToAudioResponse{" +
                "audioData=" + Arrays.toString(audioData) +
                '}';
    }
}