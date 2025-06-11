package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 会话参与人：记录会话中参与的用户，支持多用户加入群聊对象 sys_chat_session_user
 *
 * @author Tim
 * @date 2025-06-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_chat_session_user")
public class SysChatSessionUser extends BaseEntity {

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
     * 用户ID
     */
    private Long userId;

    /**
     * 加入时间
     */
    private Date joinTime;

    /**
     * 是否被禁言（0否 1是）
     */
    private Long isMuted;


}
