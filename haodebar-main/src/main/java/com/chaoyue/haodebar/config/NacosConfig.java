package com.chaoyue.haodebar.config;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/9/17 20:00
 * @version: version 1.0
 * @dec: 描述信息
 */
@Configuration
@Slf4j
public class NacosConfig {
    @Resource
    private NacosConfigProperties nacosConfigProperties;
    @Bean
    public ConfigService configService() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, nacosConfigProperties.getServerAddr());
        properties.setProperty(PropertyKeyConst.NAMESPACE, nacosConfigProperties.getNamespace());
        properties.setProperty(PropertyKeyConst.USERNAME, nacosConfigProperties.getUsername());
        properties.setProperty(PropertyKeyConst.PASSWORD, nacosConfigProperties.getPassword());
        ConfigService configService;
        try {
            configService = NacosFactory.createConfigService(properties);
        } catch (NacosException e) {
            log.error("初始化Nacos配置出错 {}", e.getMessage());
            return null;
        }
        return configService;
    }
}
