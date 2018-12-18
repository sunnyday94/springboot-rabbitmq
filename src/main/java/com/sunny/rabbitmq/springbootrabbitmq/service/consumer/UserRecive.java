/**
 * FileName: UserRecive
 * Author:   sunny
 * Date:     2018/12/18 14:58
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.service.consumer;

import com.sunny.rabbitmq.springbootrabbitmq.constants.ConstantsUtils;
import com.sunny.rabbitmq.springbootrabbitmq.metadata.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
@RabbitListener(queues = {"userQueue"})
public class UserRecive {

    @RabbitHandler
    public void process(User user){
        log.info("UserRecive接收时间:"+ ConstantsUtils.SDF.format(new Date())+"\t接收消息:"+user.getUserName()+"/"+user.getPassword());
    }
}