/**
 * FileName: SunnyReciver Author:   sunny Date:     2020/4/23 13:26 Description: History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.service.consumer;

import com.sunny.rabbitmq.springbootrabbitmq.constants.ConstantsUtils;
import com.sunny.rabbitmq.springbootrabbitmq.exception.MQListenerException;
import com.sunny.rabbitmq.springbootrabbitmq.metadata.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author sunny
 * @create 2020/4/23
 * @since 1.0.0
 */
@Component
@Slf4j
@RabbitListener(queues = "sunnyQueue")
public class SunnyReciver{


    @RabbitHandler
    public void listener(User user) throws MQListenerException {
        log.info("SunnySender接收时间:"+ ConstantsUtils.SDF.format(new Date())+"\t发送消息:"+ user);
    }
}
