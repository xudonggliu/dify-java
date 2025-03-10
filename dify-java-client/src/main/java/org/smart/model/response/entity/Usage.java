package org.smart.model.response.entity;


/**
 * 模型用量信息
 */
public class Usage {
    private int promptTokens;
    private int completionTokens;
    private int totalTokens;
    private String promptUnitPrice;
    private String promptPriceUnit;
    private String promptPrice;
    private String completionUnitPrice;
    private String completionPriceUnit;
    private String completionPrice;
    private String totalPrice;
    private String currency;
    private double latency;

    // Getters and setters

    public int getPromptTokens() {
        return promptTokens;
    }

    public void setPromptTokens(int promptTokens) {
        this.promptTokens = promptTokens;
    }

    public int getCompletionTokens() {
        return completionTokens;
    }

    public void setCompletionTokens(int completionTokens) {
        this.completionTokens = completionTokens;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public void setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
    }

    public String getPromptUnitPrice() {
        return promptUnitPrice;
    }

    public void setPromptUnitPrice(String promptUnitPrice) {
        this.promptUnitPrice = promptUnitPrice;
    }

    public String getPromptPriceUnit() {
        return promptPriceUnit;
    }

    public void setPromptPriceUnit(String promptPriceUnit) {
        this.promptPriceUnit = promptPriceUnit;
    }

    public String getPromptPrice() {
        return promptPrice;
    }

    public void setPromptPrice(String promptPrice) {
        this.promptPrice = promptPrice;
    }

    public String getCompletionUnitPrice() {
        return completionUnitPrice;
    }

    public void setCompletionUnitPrice(String completionUnitPrice) {
        this.completionUnitPrice = completionUnitPrice;
    }

    public String getCompletionPriceUnit() {
        return completionPriceUnit;
    }

    public void setCompletionPriceUnit(String completionPriceUnit) {
        this.completionPriceUnit = completionPriceUnit;
    }

    public String getCompletionPrice() {
        return completionPrice;
    }

    public void setCompletionPrice(String completionPrice) {
        this.completionPrice = completionPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getLatency() {
        return latency;
    }

    public void setLatency(double latency) {
        this.latency = latency;
    }
}