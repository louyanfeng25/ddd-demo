package com.baiyan.ddd.application.ability.share;


import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 能力上下文
 *
 * @author baiyan
 */
public class AbilityContext {

    private static final ThreadLocal<Map<String, Object>> CONTEXT = new TransmittableThreadLocal<>();

    /**
     * 初始化上下文
     */
    protected static void initContext() {
        Map<String, Object> con = CONTEXT.get();
        if (con == null) {

            CONTEXT.set(new HashMap<>(8));
        } else {
            CONTEXT.get().clear();
        }
    }

    /**
     * 清除上下文
     */
    protected static void clearContext() {
        CONTEXT.remove();
    }

    /**
     * 获取上下文内容
     *
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getValue(String key) {
        Map<String, Object> con = CONTEXT.get();
        if (con == null) {
            return null;
        }
        return (T) con.get(key);
    }

    /**
     * 设置上下文参数
     *
     * @param key
     * @param value
     */
    public static void putValue(String key, Object value) {
        Map<String, Object> con = CONTEXT.get();
        if (con == null) {
            CONTEXT.set(new HashMap<>(8));
            con = CONTEXT.get();
        }
        con.put(key, value);
    }
}
