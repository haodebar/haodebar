package com.chaoyue.haodebar.listener;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.chaoyue.haodebar.tool.BizConfigUtils;
import com.chaoyue.haodebar.tool.IpAccessConfigUtils;
import com.chaoyue.haodebar.tool.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
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

    private static final String LOG_LEVEL_DEMOTION="logLevelDemotion";

    @Resource
    private ConfigService configService;

    @Resource
    private BizConfigUtils bizConfigUtils;

    @Resource
    private IpAccessConfigUtils ipAccessConfigUtils;

    @Resource
    private MailUtils mailUtils;

    @PostConstruct
    public void init() throws NacosException {

        bizConfigListener();
        ipAccessConfigListener();
    }

    private void bizConfigListener() throws NacosException {
        ConcurrentHashMap<String, Object> oldCacheValueMap = bizConfigUtils.getCacheValueMap();

        ConcurrentHashMap<String, Object> newCacheValueMap =new ConcurrentHashMap<>();

        String config = "";
        try {
            config = configService.getConfig(bizConfigUtils.getBizConfig(), bizConfigUtils.getGroup(), 3000);
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

        configService.addListener(bizConfigUtils.getBizConfig(), bizConfigUtils.getGroup(),new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String value) {
                ConcurrentHashMap<String, Object> oldCacheValueMap = bizConfigUtils.getCacheValueMap();
                ConcurrentHashMap<String, Object> newCacheValueMap = new ConcurrentHashMap<>();
                log.info("nacos config change value={}", value);
                String config = "";
                try {
                    config = configService.getConfig(bizConfigUtils.getBizConfig(), bizConfigUtils.getGroup(), 3000);
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
                setLogLevelDemotion(bizConfigUtils.getStringValue(LOG_LEVEL_DEMOTION));
            }
        });
    }

    private void ipAccessConfigListener() throws NacosException{
        ConcurrentHashMap<String, List<String>> oldCacheValueMap = ipAccessConfigUtils.getIpAccessListMap();

        ConcurrentHashMap<String, List<String>> newCacheValueMap =new ConcurrentHashMap<>();

        String config = "";
        try {
            config = configService.getConfig(ipAccessConfigUtils.getBizConfig(), ipAccessConfigUtils.getGroup(), 3000);
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
            String[] value =entry.getValue().toString().split(";");
            newCacheValueMap.put(key, new ArrayList<>(Arrays.asList(value)));
        }
        ipAccessConfigUtils.setIpAccessListMap(newCacheValueMap);

        configMapChange(oldCacheValueMap,newCacheValueMap);

        configService.addListener(ipAccessConfigUtils.getBizConfig(), ipAccessConfigUtils.getGroup(),new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String value) {
                ConcurrentHashMap<String, List<String>> oldCacheValueMap = ipAccessConfigUtils.getIpAccessListMap();
                ConcurrentHashMap<String, List<String>> newCacheValueMap = new ConcurrentHashMap<>();
                log.info("nacos config change value={}", value);
                String config = "";
                try {
                    config = configService.getConfig(ipAccessConfigUtils.getBizConfig(), ipAccessConfigUtils.getGroup(), 3000);
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
                    String[] valueTwo =entry.getValue().toString().split(";");
                    newCacheValueMap.put(key, new ArrayList<>(Arrays.asList(valueTwo)));
                }

                ipAccessConfigUtils.setIpAccessListMap(newCacheValueMap);
                configMapChange(oldCacheValueMap,newCacheValueMap);
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
        mailUtils.sendSimpleMail(mailUtils.getToMailUser(),mailUtils.getSubject(),message);
    }
}
