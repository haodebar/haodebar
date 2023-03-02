package com.chaoyue.haodebar.mq.producer;

import com.chaoyue.haodebar.mq.constants.MqConstants;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2022/11/16 17:01
 * @version: version 1.0
 * @dec: 描述信息
 */
public interface MessageProducerChannel {
    /**
     * 生产消息
     *
     * @return
     */
    @Output(MqConstants.DEFAULT_OUTPUT)
    MessageChannel produce();
}
