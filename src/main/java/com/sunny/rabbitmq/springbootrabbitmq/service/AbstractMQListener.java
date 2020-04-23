/**
 * FileName: AbstractMQListener Author:   sunny Date:     2020/4/22 11:20 Description: History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.rabbitmq.client.Channel;
import com.sunny.rabbitmq.springbootrabbitmq.controller.vo.MQErrorVO;
import com.sunny.rabbitmq.springbootrabbitmq.exception.MQListenerException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author sunny
 * @create 2020/4/22
 * @since 1.0.0
 */
@Slf4j
public abstract  class AbstractMQListener<T> implements MQListener<T>{

    //出错次数
    private int errorCount = 0;

    @Autowired(required = false)
    private SimpleEmail simpleEmail;

    @Value("${mail.to}")
    private String[] mailTo;

    @Override
    public String messageBody(Message message) {
        byte[] body = message.getBody();
        if (body==null || Array.getLength(body)==0) {
            return null;
        }
        return new String(body, StandardCharsets.UTF_8);
    }

    @Override
    public T convertMessage(String jsonMsg) {
        if(jsonMsg==null || "".equals(jsonMsg)){
            return null;
        }
        return JSON.parseObject(jsonMsg, getType() );
    }

    @Override
    public abstract void listener(T msg) throws MQListenerException;


    @Override
    public void onErrorHandler(Channel channel, Message message, Throwable throwable) {
        this.errorCount++;
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try{
            if(errorCount>3){
                //错误次数超过三次，拒绝并删除消息,同时发送邮件，否则拒绝重发
                channel.basicReject(deliveryTag,false);
                MQErrorVO vo = new MQErrorVO();
                vo.setLocalDateTime(LocalDateTime.now());
                vo.setErrorMsg(ExceptionUtils.getMessage(throwable));
                vo.setMessageJson(messageBody(message));
                sendEmail(vo);
            }
            channel.basicReject(deliveryTag, true);
        }catch (Exception ex){
            log.error("错误消息处理发生异常:",ex);
        }
    }


    private void sendEmail(MQErrorVO errorVO){
        try {
            simpleEmail.setSubject("[MQ_ERROR]");
            simpleEmail.setMsg("发送消息数据:"+errorVO.getMessageJson()+"\n异常信息:"+
                    errorVO.getErrorMsg());
            simpleEmail.addTo(mailTo);
            if (null == simpleEmail.getMimeMessage()) {
                simpleEmail.buildMimeMessage();
            }
            simpleEmail.sendMimeMessage();
        } catch (Exception e) {
            log.error("发送邮件出现异常:",e);
        }

    }

    /* *
     * @Author sunny
     * @Description  没有抛异常则ack
     * @Date 12:02 2020/4/22
     * @Param [message, channel]
     * @return void
     **/
    @Override
    public void onMessage(Message message, Channel channel){
        String json = "";
        try{
           json =  messageBody(message);
           T msg = null;
           try{
              msg  = convertMessage(json);
           }catch (JSONException jsonException){
               log.error("监听消息json解析异常,json信息:{},异常:",json,jsonException);
           }
           listener(msg);
           long deliveryTag = message.getMessageProperties().getDeliveryTag();
           channel.basicAck(deliveryTag, false);
        }catch (Exception ex){
            log.error("监听消息出现异常,消息体:{}，异常:",json, ex);
            errorCount++;
            onErrorHandler(channel, message, ex);
        }
    }

    private Type getType(){
        return ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
