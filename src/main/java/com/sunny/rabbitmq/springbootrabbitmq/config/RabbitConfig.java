/**
 * FileName: RabbitConfig
 * Author:   sunny
 * Date:     2018/12/19 17:00
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @author sunny
 * @create 2018/12/19
 * @since 1.0.0
 */
@Slf4j
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String addresses;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;

    @Value("${spring.rabbitmq.publisher-returns}")
    private boolean publisherReturns;

    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses+":"+port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        //confirm保证达到交换机，return保证交换机到达队列
        /*如果消息没有到exchange,则confirm回调,ack=false；
          如果消息到达exchange,则confirm回调,ack=true；*/
        connectionFactory.setPublisherConfirms(publisherConfirms);
        /*exchange到queue成功,则不回调return
          exchange到queue失败,则回调return(需设置mandatory=true,否则不会回调,消息就丢了)*/
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    @Bean
    /** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }

}
