package org.smart.model.response.entity;

public class MessageFile {
    /**
     * type (string) 支持类型：
     * document 具体类型包含：'TXT', 'MD', 'MARKDOWN', 'PDF', 'HTML', 'XLSX', 'XLS', 'DOCX', 'CSV', 'EML', 'MSG', 'PPTX', 'PPT', 'XML', 'EPUB'
     * image 具体类型包含：'JPG', 'JPEG', 'PNG', 'GIF', 'WEBP', 'SVG'
     * audio 具体类型包含：'MP3', 'M4A', 'WAV', 'WEBM', 'AMR'
     * video 具体类型包含：'MP4', 'MOV', 'MPEG', 'MPGA'
     * custom 具体类型包含：其他文件类型
     */
    private String type;

    /**
     * transfer_method (string) 传递方式:
     * remote_url: 图片地址。
     * local_file: 上传文件
     */
    private String transfer_method;

    /**
     *  图片地址。（仅当传递方式为 remote_url 时）。
     */
    private String url;

    /**
     * 上传文件 ID。（仅当传递方式为 local_file 时）
     */
    private String upload_file_id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransfer_method() {
        return transfer_method;
    }

    public void setTransfer_method(String transfer_method) {
        this.transfer_method = transfer_method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUpload_file_id() {
        return upload_file_id;
    }

    public void setUpload_file_id(String upload_file_id) {
        this.upload_file_id = upload_file_id;
    }
}
