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
import org.springframework.util.Assert;

import java.util.UUID;

import javax.annotation.Resource;

/**
 * @description
 * @author sunny
 * @create 2018/12/19
 * @since 1.0.0
 */
@Component
@Slf4j
public class CallBackSender/* implements RabbitTemplate.ConfirmCallback */{

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
//        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
                log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) ->
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        String msg="callbackSender : I am callback sender";
        log.info("CallBackSender:{}",msg);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        log.info("callbackSender UUID: {}" ,correlationData.getId());
        rabbitTemplate.convertAndSend("topicExchange", "topic.messages", msg, correlationData);
    }

    //交换机Exchange确认收到了消息
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        log.info("callbakck confirm:{}", correlationData.getId());
//    }

}