/**
 * FileName: CallBackSender
 * Author:   sunny
 * Date:     2018/12/19 17:02
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.service.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @description
 * @author sunny
 * @create 2018/12/19
 * @since 1.0.0
 */
@Component
@Slf4j
public class CallBackSender implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate myRabbitTemplate;

    public void sendMessage() {
        myRabbitTemplate.setConfirmCallback(this);
        String msg="callbackSender : I am callback sender";
        log.info("CallBackSender:{}",msg);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        log.info("callbackSender UUID: {}" ,correlationData.getId());
        myRabbitTemplate.convertAndSend("topicExchange", "topic.messages", msg, correlationData);
    }

    //交换机Exchange确认收到了消息
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("callbakck confirm:{}", correlationData.getId());
    }

}