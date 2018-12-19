/**
 * FileName: TopicSender
 * Author:   sunny
 * Date:     2018/12/19 14:29
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.service.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description
 * @author sunny
 * @create 2018/12/19
 * @since 1.0.0
 */
@Component
@Slf4j
public class TopicSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(){
        String msg1 = "I am topic.mesaage msg======";
        log.info("sender1:{}",msg1);
        amqpTemplate.convertAndSend("topicExchange", "topic.message", msg1);

        String msg2 = "I am topic.mesaages msg########";
        log.info("sender2:{}",msg2);
        amqpTemplate.convertAndSend("topicExchange", "topic.messages", msg2);
    }
}