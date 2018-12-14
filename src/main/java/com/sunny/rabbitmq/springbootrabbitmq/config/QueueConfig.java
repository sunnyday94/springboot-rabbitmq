/**
 * FileName: QueueConfig
 * Author:   sunny
 * Date:     2018/12/14 11:20
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description
 * @author sunny
 * @create 2018/12/14
 * @since 1.0.0
 */
@Configuration
public class QueueConfig {
    //以下创建队列
    @Bean
    public Queue helloQueue(){
        return new Queue("helloQueue");
    }

    @Bean
    public Queue userQueue(){
        return new Queue("userQueue");
    }



    //===============以下是验证topic Exchange的队列==========
    @Bean
    public Queue queueMessage1() {
        return new Queue("topic.message1");
    }

    @Bean
    public Queue queueMessage2() {
        return new Queue("topic.message2");
    }
    //===============以上是验证topic Exchange的队列==========




    //===============以下是验证Fanout Exchange的队列==========
    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }
    //===============以上是验证Fanout Exchange的队列==========



    //创建交换机

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }


    /**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
     * @param queueMessage2
     * @param topicExchange
     * @return
     */
    @Bean
    public Binding bindingExchangeMessages(Queue queueMessage2, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMessage2).to(topicExchange).with("topic.#");
    }


    @Bean
    public Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }

    @Bean
    public Binding bindingDirectExchange(Queue helloQueue,DirectExchange directExchange){
        return BindingBuilder.bind(helloQueue).to(directExchange).with("helloQueue");
    }

}