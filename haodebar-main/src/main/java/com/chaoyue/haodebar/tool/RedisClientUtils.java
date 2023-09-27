package com.chaoyue.haodebar.tool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/9/26 0:03
 * @version: version 1.0
 * @dec: 描述信息
 */
@Component
@Slf4j
public class RedisClientUtils {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 获取String value
     * @param key
     * @return
     */
    public String getString(String key){
        String value =null;
        try {
            Object objectValue = redisTemplate.opsForValue().get(key);
            if(Objects.nonNull(objectValue)){
                value =String.valueOf(objectValue);
            }
        }catch (Exception e){
            log.error("get redis string value error",e);
        }
        return value;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void setString(String key,String value){

    }
}
