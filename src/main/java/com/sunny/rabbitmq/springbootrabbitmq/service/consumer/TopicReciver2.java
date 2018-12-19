/**
 * FileName: TopicReciver2
 * Author:   sunny
 * Date:     2018/12/19 14:44
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description
 * @author sunny
 * @create 2018/12/19
 * @since 1.0.0
 */
@Component
@Slf4j
@RabbitListener(queues = {"topic.messages"})
public class TopicReciver2 {

    @RabbitHandler
    public void process(String msg){
        log.info("TopicReciver2:{}",msg);
    }
}