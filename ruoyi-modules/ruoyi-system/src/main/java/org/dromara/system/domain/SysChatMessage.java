package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 消息：用于记录聊天内容对象 sys_chat_message
 *
 * @author Tim
 * @date 2025-06-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_chat_message")
public class SysChatMessage extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 会话ID
     */
    private Long sessionId;

    /**
     * 发送者ID
     */
    private Long senderId;

    /**
     * 消息内容（支持文本、图片、文件等）
     */
    private String content;

    /**
     * 消息类型（1=文本，2=图片，3=文件等）
     */
    private Long messageType;


    public void setSendTime(LocalDateTime now) {
    }
}
