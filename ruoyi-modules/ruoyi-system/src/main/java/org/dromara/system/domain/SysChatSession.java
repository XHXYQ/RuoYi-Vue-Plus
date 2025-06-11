package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 会话：用于记录会话（聊天房间），支持私聊或群聊对象 sys_chat_session
 *
 * @author Tim
 * @date 2025-06-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_chat_session")
public class SysChatSession extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 会话类型（1=私聊，2=群聊）
     */
    private Long sessionType;

    /**
     * 创建人用户ID
     */
    private Long creatorId;


}
