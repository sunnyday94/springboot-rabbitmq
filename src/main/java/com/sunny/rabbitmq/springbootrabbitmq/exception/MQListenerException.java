/**
 * FileName: MQListenerException Author:   sunny Date:     2020/4/22 11:11 Description: History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.exception;

/**
 * 〈一句话功能简述〉<br> 
 * 〈监听异常〉
 *
 * @author sunny
 * @create 2020/4/22
 * @since 1.0.0
 */
public class MQListenerException extends Exception{

    public MQListenerException(){

    }

    public MQListenerException(String msg){
        super(msg);
    }

    public MQListenerException(String msg,Throwable throwable){
        super(msg,throwable);
    }
}
