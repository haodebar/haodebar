package com.chaoyue.haodebar.mq;

import com.chaoyue.haodebar.mq.constants.MqConstants;
import com.chaoyue.haodebar.mq.consumer.MessageConsumer;
import com.chaoyue.haodebar.mq.producer.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
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
@EnableBinding(MessageProducer.class)
@Slf4j
@Component
public class MqProcessor {
    @Resource
    MessageProducer messageProducer;

    public void send(String message){
        messageProducer.produce().send(MessageBuilder.withPayload(message).build());
    }
    /*@StreamListener(MqConstants.DEFAULT_INPUT)
    public String receive(String message){
        if(log.isInfoEnabled()){
            log.info("receive message {}",message);
        }
        return message;
    }*/
}
