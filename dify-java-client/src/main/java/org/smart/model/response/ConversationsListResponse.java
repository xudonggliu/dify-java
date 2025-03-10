package org.smart.model.response;

import org.smart.model.response.entity.Conversation;

import java.util.List;

/**
 * 会话列表响应模型
 */
public class ConversationsListResponse {
    private int limit;
    private boolean hasMore;
    private List<Conversation> data;

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

    public List<Conversation> getData() {
        return data;
    }

    public void setData(List<Conversation> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ConversationsListResponse{" +
                "limit=" + limit +
                ", hasMore=" + hasMore +
                ", data=" + data +
                '}';
    }
}