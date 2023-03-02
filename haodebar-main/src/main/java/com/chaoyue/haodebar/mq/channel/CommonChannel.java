package com.chaoyue.haodebar.mq.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/3/2 11:33
 * @version: version 1.0
 * @dec: 描述信息
 */
public interface CommonChannel {
    String INPUT = "steam-input";
    String OUTPUT = "steam-output";

    /**
     * input
     * @return SubscribableChannel
     */
    @Input(INPUT)
    SubscribableChannel consumeChannel();

    /**
     * output
     * @return MessageChannel
     */
    @Output(OUTPUT)
    MessageChannel produceChannel();
}
