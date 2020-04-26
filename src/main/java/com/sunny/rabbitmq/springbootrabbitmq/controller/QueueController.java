/**
 * FileName: QueueController
 * Author:   sunny
 * Date:     2018/12/14 11:51
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.controller;

import com.sunny.rabbitmq.springbootrabbitmq.service.producer.*;

import org.jboss.logging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @author sunny
 * @create 2018/12/14
 * @since 1.0.0
 */
@RestController
@RequestMapping(value="/rabbitmq")
@Slf4j
public class QueueController {

    //生产者1
    private HelloSender helloSender1;

    //构造方法注入
    @Autowired //该注解在这里可加可不加
    public QueueController(HelloSender helloSender){
        log.info(MessageFormat.format(">>>>>QueueController构造方法被调用,注入了HelloSender对象:{0}", helloSender));
        this.helloSender1 = helloSender;
    }

    //生产者2
    @Autowired  //Marks a constructor, field, setter method or config method as to be autowired by Spring's dependency injection facilities.
    private HelloSender2 helloSender2;

    @Autowired
    private UserSender userSender;

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private CallBackSender callBackSender;

    private SunnySender sunnySender;

    //set注入
    @Autowired
    public void setSunnySender(SunnySender sunnySender){
        this.sunnySender = sunnySender;
    }

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


    @GetMapping(value="testFanout")
    public String testFanout(){
        fanoutSender.sendMessage();
        return "消息已发送";
    }


    /**
     * @Description: 带callback的消息发送
     * @Author: sunny
     * @Date: 2018/12/19 17:11
     */
    @GetMapping(value="testCallBack")
    public String testCallBack(){
        callBackSender.sendMessage();
        return "消息已发送";
    }


    /* *
     * @Author sunny
     * @Description  sunnySendMsg
     * @Date 12:02 2020/4/23
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping(value="sunnySendMsg")
    public String sunnySendMsg(){
        sunnySender.sendMsg();
        return "sunnySendMsg success!";
    }

}