/**
 * FileName: UserSender
 * Author:   sunny
 * Date:     2018/12/18 14:52
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.service.producer;

import com.sunny.rabbitmq.springbootrabbitmq.constants.ConstantsUtils;
import com.sunny.rabbitmq.springbootrabbitmq.metadata.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description
 * @author sunny
 * @create 2018/12/18
 * @since 1.0.0
 */
@Component
@Slf4j
public class UserSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendUser(){
        User user = new User();
        user.setUserName("sunny");
        user.setPassword("123456");
        log.info("UserSender发送时间:"+ ConstantsUtils.SDF.format(new Date())+"\t发送消息:"+user.getUserName()+"/"+user.getPassword());
        amqpTemplate.convertAndSend("userQueue",user);
    }
}