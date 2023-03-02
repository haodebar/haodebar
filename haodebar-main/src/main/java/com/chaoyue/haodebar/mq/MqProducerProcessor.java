package com.chaoyue.haodebar.mq;

import com.chaoyue.haodebar.mq.channel.CommonChannel;
import com.chaoyue.haodebar.mq.producer.MessageProducerChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2022/11/16 17:24
 * @version: version 1.0
 * @dec: 描述信息
 */
@EnableBinding(CommonChannel.class)
@Slf4j
@Component
public class MqProducerProcessor {
    @Autowired
    CommonChannel commonChannel;

    public void send(String message){
        try{
            commonChannel.produceChannel().send(MessageBuilder.withPayload(message).build());
        }catch (Exception e){
            log.info("send kafka message error ={}",e.getMessage());
        }
    }

}
