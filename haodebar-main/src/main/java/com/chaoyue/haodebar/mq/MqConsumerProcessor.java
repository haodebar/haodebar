package com.chaoyue.haodebar.mq;

import com.chaoyue.haodebar.mq.constants.MqConstants;
import com.chaoyue.haodebar.mq.consumer.MessageConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2022/11/16 20:14
 * @version: version 1.0
 * @dec: 描述信息
 */
@Component
@EnableBinding(MessageConsumer.class)
@Slf4j
public class MqConsumerProcessor {
    @StreamListener(MqConstants.DEFAULT_INPUT)
    public void receive(String message){
        if(log.isInfoEnabled()){
            log.info("receive message {}",message);
        }
        //return message;
    }
}
