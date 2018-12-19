/**
 * FileName: FanoutSender
 * Author:   sunny
 * Date:     2018/12/19 16:33
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
public class FanoutSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(){
        String msg = "hello,I am sunny!";
        log.info("FanoutSender:{}",msg);
        //这里即使我指定了routingKey，此消息依旧会广播出去，发送给绑定了该交换机的所有队列
        amqpTemplate.convertAndSend("fanoutExchange", "anyRoutingKey", msg);
    }
}