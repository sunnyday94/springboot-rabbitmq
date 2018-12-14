/**
 * FileName: QueueController
 * Author:   sunny
 * Date:     2018/12/14 11:51
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.controller;

import com.sunny.rabbitmq.springbootrabbitmq.service.producer.HelloSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author sunny
 * @create 2018/12/14
 * @since 1.0.0
 */
@RestController
@RequestMapping(value="/rabbitmq")
public class QueueController {

    @Autowired
    private HelloSender helloSender1;

    @GetMapping(value="hello")
    public String hello(){
        helloSender1.sendMessage("hello1");
        return "消息已发送!";
    }

}