/**
 * FileName: FanoutReciverA
 * Author:   sunny
 * Date:     2018/12/19 16:38
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
@RabbitListener(queues = {"fanout.A"})
public class FanoutReciverA {

    @RabbitHandler
    public void process(String msg){
        log.info("FanoutRevicerA:{}",msg);
    }
}