/**
 * FileName: SunnySender Author:   sunny Date:     2020/4/23 11:58 Description: SunnySender
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.service.producer;

import com.alibaba.fastjson.JSONObject;
import com.sunny.rabbitmq.springbootrabbitmq.constants.ConstantsUtils;
import com.sunny.rabbitmq.springbootrabbitmq.metadata.User;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * 〈一句话功能简述〉<br> 
 * 〈SunnySender〉
 *
 * @author sunny
 * @create 2020/4/23
 * @since 1.0.0
 */
@Service
@Slf4j
public class SunnySender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMsg(){
        User user = new User();
        user.setUserName("sunny").setPassword("123456");
        log.info("SunnySender发送时间:"+ ConstantsUtils.SDF.format(new Date())+"\t发送消息:"+ user);
        amqpTemplate.convertAndSend("sunnyQueue",user);
    }
}
