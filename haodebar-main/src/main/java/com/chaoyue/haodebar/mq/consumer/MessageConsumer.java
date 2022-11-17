package com.chaoyue.haodebar.mq.consumer;

import com.chaoyue.haodebar.mq.constants.MqConstants;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2022/11/16 17:07
 * @version: version 1.0
 * @dec: 描述信息
 */
@Component
public interface MessageConsumer {
    /**
     * 消费消息
     * @return
     */
    @Input(MqConstants.DEFAULT_INPUT)
    SubscribableChannel consume();
}
