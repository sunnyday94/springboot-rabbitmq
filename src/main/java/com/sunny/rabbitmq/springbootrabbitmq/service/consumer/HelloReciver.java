/**
 * FileName: HelloReciver1
 * Author:   sunny
 * Date:     2018/12/14 11:49
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
 * @create 2018/12/14
 * @since 1.0.0
 */
@Component
@RabbitListener(queues = {"helloQueue"})  //监听哪个routingKey
@Slf4j
public class HelloReciver {

    @RabbitHandler
    public void processMsg(String msg){
        String reciveMsg = msg+"\t"+ "接收时间:"+ConstantsUtils.SDF.format(new Date());
        log.info("HelloReciver:{}",reciveMsg);
    }
}