package org.smart.model.response;


import java.util.Map;

/**
 * 应用 Meta 信息响应模型
 */
public class AppMetaResponse {
    private Map<String, Object> toolIcons;

    // Getters and setters
    public Map<String, Object> getToolIcons() {
        return toolIcons;
    }

    public void setToolIcons(Map<String, Object> toolIcons) {
        this.toolIcons = toolIcons;
    }

    @Override
    public String toString() {
        return "AppMetaResponse{" +
                "toolIcons=" + toolIcons +
                '}';
    }
}