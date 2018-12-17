/**
 * FileName: HelloReciver2
 * Author:   sunny
 * Date:     2018/12/17 15:21
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.service.consumer;

import com.sunny.rabbitmq.springbootrabbitmq.constants.ConstantsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description
 * @author sunny
 * @create 2018/12/17
 * @since 1.0.0
 */
@RabbitListener(queues = {"helloQueue"})
@Component
@Slf4j
public class HelloReciver2 {
    @RabbitHandler
    public void processMsg(String msg){
        log.info("HelloReciver2接收时间:"+ ConstantsUtils.SDF.format(new Date())+"\t接收消息:"+msg);
    }
}