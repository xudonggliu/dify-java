package org.smart.model.response.entity;

public class MessageFileVo {
    private String id;

    //  (string) 文件类型，image 图片
    String type;

    // (string) 预览图片地址
    String url;

    // (string) 文件归属方，user 或 assistant
    String belongsTo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(String belongsTo) {
        this.belongsTo = belongsTo;
    }
}
