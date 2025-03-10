package org.smart.model.response;

import org.smart.model.response.entity.FileUpload;
import org.smart.model.response.entity.SystemParameters;

import java.util.List;

/**
 * 应用参数响应模型
 */
public class AppParametersResponse {
    private String introduction;
    private List<Object> userInputElement;
    private FileUpload fileUpload;
    private SystemParameters systemParameters;

    // Getters and setters
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<Object> getUserInputElement() {
        return userInputElement;
    }

    public void setUserInputElement(List<Object> userInputElement) {
        this.userInputElement = userInputElement;
    }

    public FileUpload getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }

    public SystemParameters getSystemParameters() {
        return systemParameters;
    }

    public void setSystemParameters(SystemParameters systemParameters) {
        this.systemParameters = systemParameters;
    }

    @Override
    public String toString() {
        return "AppParametersResponse{" +
                "introduction='" + introduction + '\'' +
                ", userInputElement=" + userInputElement +
                ", fileUpload=" + fileUpload +
                ", systemParameters=" + systemParameters +
                '}';
    }
}