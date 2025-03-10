package org.smart.model.response;

import org.smart.model.response.entity.HistoryMessageFile;

import java.util.List;

/**
 * 历史消息响应模型
 */
public class MessagesHistoryResponse {
    private int limit;
    private boolean hasMore;
    private List<HistoryMessageFile> data;

    // Getters and setters
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean hasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<HistoryMessageFile> getData() {
        return data;
    }

    public void setData(List<HistoryMessageFile> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MessagesHistoryResponse{" +
                "limit=" + limit +
                ", hasMore=" + hasMore +
                ", data=" + data +
                '}';
    }
}