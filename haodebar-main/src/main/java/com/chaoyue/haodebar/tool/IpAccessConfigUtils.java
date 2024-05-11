package com.chaoyue.haodebar.tool;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@Data
public class IpAccessConfigUtils {
    private  final String ipSplit = ";";

    private  final String bizConfig = "ip-access-config-dev.yaml";

    private  final String group = "DEFAULT_GROUP";


    /**
     * 本地缓存Map
     */
    private ConcurrentHashMap<String, List<String>> ipAccessListMap;

}
