package org.smart.model.response.entity;


/**
 * 系统参数
 */
public class SystemParameters {
    private int fileSizeLimit;
    private int imageFileSizeLimit;
    private int audioFileSizeLimit;
    private int videoFileSizeLimit;

    // Getters and setters

    public int getFileSizeLimit() {
        return fileSizeLimit;
    }

    public void setFileSizeLimit(int fileSizeLimit) {
        this.fileSizeLimit = fileSizeLimit;
    }

    public int getImageFileSizeLimit() {
        return imageFileSizeLimit;
    }

    public void setImageFileSizeLimit(int imageFileSizeLimit) {
        this.imageFileSizeLimit = imageFileSizeLimit;
    }

    public int getAudioFileSizeLimit() {
        return audioFileSizeLimit;
    }

    public void setAudioFileSizeLimit(int audioFileSizeLimit) {
        this.audioFileSizeLimit = audioFileSizeLimit;
    }

    public int getVideoFileSizeLimit() {
        return videoFileSizeLimit;
    }

    public void setVideoFileSizeLimit(int videoFileSizeLimit) {
        this.videoFileSizeLimit = videoFileSizeLimit;
    }
}