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
import com.sunny.rabbitmq.springbootrabbitmq.service.producer.HelloSender2;
import com.sunny.rabbitmq.springbootrabbitmq.service.producer.TopicSender;
import com.sunny.rabbitmq.springbootrabbitmq.service.producer.UserSender;
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

    //生产者1
    @Autowired
    private HelloSender helloSender1;

    //生产者2
    @Autowired
    private HelloSender2 helloSender2;

    @Autowired
    private UserSender userSender;

    @Autowired
    private TopicSender topicSender;

    @GetMapping(value="hello")
    public String hello(){
        helloSender1.sendMessage("hello1");
        return "消息已发送!";
    }


    @GetMapping(value="oneToMany")
    public String onToMany(){
        for(int i=1;i<=10;i++){
            helloSender1.sendMessage("hello"+i);
        }
        return "消息已发送";
    }


    @GetMapping(value="manyToMany")
    public String manyToMany(){
        for(int i = 1;i<=10;i++){
            helloSender1.sendMessage("hello"+i);
            helloSender2.sendMessage("hello"+i);
        }
        return "消息已发送";
    }


    @GetMapping(value="sendUser")
    public String sendUser(){
        userSender.sendUser();
        return "消息已发送";
    }


    @GetMapping(value="testTopic")
    public String testTopic(){
        topicSender.sendMessage();
        return "消息已发送";
    }

}