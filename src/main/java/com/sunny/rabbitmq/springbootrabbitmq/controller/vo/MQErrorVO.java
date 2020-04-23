/**
 * FileName: MQErrorVO Author:   sunny Date:     2020/4/23 11:28 Description: MQ错误信息VO History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sunny.rabbitmq.springbootrabbitmq.controller.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br> 
 * 〈MQ错误信息VO〉
 *
 * @author sunny
 * @create 2020/4/23
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MQErrorVO implements Serializable {

    private static final long serialVersionUID = 445483687739875613L;
    
    private String messageJson;

    private String errorMsg;

    private LocalDateTime localDateTime;
}
