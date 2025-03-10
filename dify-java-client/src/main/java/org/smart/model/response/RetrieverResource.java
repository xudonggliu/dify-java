package org.smart.model.response;


/**
 * 引用和归属分段列表项
 */
public class RetrieverResource {
    private int position;
    private String datasetId;
    private String datasetName;
    private String documentId;
    private String documentName;
    private String segmentId;
    private double score;
    private String content;

    // Getters and setters

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RetrieverResource{" +
                "position=" + position +
                ", datasetId='" + datasetId + '\'' +
                ", datasetName='" + datasetName + '\'' +
                ", documentId='" + documentId + '\'' +
                ", documentName='" + documentName + '\'' +
                ", segmentId='" + segmentId + '\'' +
                ", score=" + score +
                ", content='" + content + '\'' +
                '}';
    }
}