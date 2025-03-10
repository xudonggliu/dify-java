package org.smart.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

public class JSONUtil {
    /**
     * 将对象转换为 JSON 字符串
     *
     * @param object 对象
     * @return JSON 字符串
     */
    public static String toJSONString(Object object) {
        try {
            return JSON.toJSONString(object);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert object to JSON string", e);
        }
    }

    /**
     * 将 JSON 字符串解析为对象
     *
     * @param json  JSON 字符串
     * @param clazz 目标类
     * @param <T>   目标类型
     * @return 解析后的对象
     */
    public static <T> T parseJSON(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON string to object", e);
        }
    }
}