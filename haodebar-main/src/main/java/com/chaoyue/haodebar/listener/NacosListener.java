package com.chaoyue.haodebar.listener;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.chaoyue.haodebar.utils.BizConfigUtils;
import com.chaoyue.haodebar.utils.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/9/17 17:54
 * @version: version 1.0
 * @dec: 描述信息
 */
@Component
@Slf4j
public class NacosListener{
    private static final String bizConfigDev = "biz-config-dev.yaml";

    private static final String group = "DEFAULT_GROUP";

    private static final String toMailUser="1352425876@qq.com";
    private static final String subject="配置发生变化";

    private static final String logLevelDemotion="logLevelDemotion";
    private static final String logLevelDemotionFlag="true";

    @Resource
    private ConfigService configService;

    @Resource
    private BizConfigUtils bizConfigUtils;

    @Resource
    private MailUtils mailUtils;

    @PostConstruct
    public void init() throws NacosException, IOException {

        ConcurrentHashMap<String, Object> oldCacheValueMap = bizConfigUtils.getCacheValueMap();

        ConcurrentHashMap<String, Object> newCacheValueMap =new ConcurrentHashMap<>();

        String config = null;
        try {
            config = configService.getConfig(bizConfigDev, group, 3000);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        try {
            properties.load(new ByteArrayInputStream(config.getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //遍历所有参数生成MAP
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = entry.getKey().toString().trim();
            newCacheValueMap.put(key, entry.getValue());
        }
        bizConfigUtils.setCacheValueMap(newCacheValueMap);

        configMapChange(oldCacheValueMap,newCacheValueMap);

        configService.addListener(bizConfigDev, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String value) {
                ConcurrentHashMap<String, Object> oldCacheValueMap = bizConfigUtils.getCacheValueMap();
                ConcurrentHashMap<String, Object> newCacheValueMap = new ConcurrentHashMap<>();
                log.info("nacos config change value={}", value);
                String config = null;
                try {
                    config = configService.getConfig(bizConfigDev, group, 3000);
                } catch (NacosException e) {
                    e.printStackTrace();
                }
                Properties properties = new Properties();
                try {
                    properties.load(new ByteArrayInputStream(config.getBytes(StandardCharsets.UTF_8)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //遍历所有参数生成MAP
                for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                    String key = entry.getKey().toString().trim();
                    newCacheValueMap.put(key, entry.getValue());
                }

                bizConfigUtils.setCacheValueMap(newCacheValueMap);
                configMapChange(oldCacheValueMap,newCacheValueMap);

                //修改日志级别
                setLogLevelDemotion(bizConfigUtils.getStringValue(logLevelDemotion));
            }
        });
    }

    /**
     * 通过配置修改日志级别
     * @param classInfoLevel
     */
    private void setLogLevelDemotion(String classInfoLevel) {
        if(StringUtils.isNotEmpty(classInfoLevel)){
            Map<String,String> classInfoLevelMap =JSONObject.parseObject(classInfoLevel,Map.class);
            if(!classInfoLevelMap.isEmpty()){
                classInfoLevelMap.forEach((key,value)->{
                    //修改日志级别
                    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
                    List<Logger> loggerList = loggerContext.getLoggerList();
                    List<Logger> packageLoggerList = loggerList.stream().filter(s->s.getName().startsWith(key)).collect(Collectors.toList());
                    for (Logger logger : packageLoggerList) {
                        logger.setLevel(Level.toLevel(value));
                    }
                });
            }
        }

    }


    public void configMapChange(ConcurrentHashMap oldConcurrentHashMap,ConcurrentHashMap newConcurrentHashMap){
        String message = "old="+ JSONObject.toJSONString(oldConcurrentHashMap)+";\n"+"new="+JSONObject.toJSONString(newConcurrentHashMap);
        log.info("bizConfig change old={},new={}",oldConcurrentHashMap,newConcurrentHashMap);
        mailUtils.sendSimpleMail(toMailUser,subject,message);
    }
}
