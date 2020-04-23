/**
 * FileName: MQListener Author:   sunny Date:     2020/4/22 11:07 Description: History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.service;

import com.rabbitmq.client.Channel;
import com.sunny.rabbitmq.springbootrabbitmq.exception.MQListenerException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author sunny
 * @create 2020/4/22
 * @since 1.0.0
 */
public interface MQListener<T> extends ChannelAwareMessageListener {

    /* *
     * @Author sunny
     * @Description  消息转json
     * @Date 11:18 2020/4/22
     * @Param [message]
     * @return java.lang.String
     **/
    String messageBody(Message message);

    /* *
     * @Author sunny
     * @Description  json数据转对应数据类型
     * @Date 11:19 2020/4/22
     * @Param [jsonMsg]
     * @return T
     **/
    T convertMessage(String jsonMsg);

    /* *
     * @Author sunny
     * @Description  消息监听
     * @Date 11:19 2020/4/22
     * @Param [msg]
     * @return void
     **/
    void listener(T msg) throws MQListenerException;


    /* *
     * @Author sunny
     * @Description  消息错误处理
     * @Date 11:19 2020/4/22
     * @Param [channel, message, throwable]
     * @return void
     **/
    void onErrorHandler(Channel channel, Message message,Throwable throwable);
}
