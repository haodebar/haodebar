package com.chaoyue.haodebar.tool;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/8/23 11:38
 * @version: version 1.0
 * @dec: nacos获取配置工具类
 */
@Component
@Slf4j
@Data
public class BizConfigUtils {
    private final static String bizConfigDev = "biz-config-dev.yaml";
    /**
     * 本地缓存Map
     */
    private ConcurrentHashMap<String, Object> cacheValueMap;

    public String getStringValue(String key) {
        return String.valueOf(cacheValueMap.get(key));
    }

    public Integer getInteger(String key) {
        return Integer.valueOf(String.valueOf(cacheValueMap.get(key)));
    }

    public Long getLong(String key) {
        return Long.valueOf(String.valueOf(cacheValueMap.get(key)));
    }


}
