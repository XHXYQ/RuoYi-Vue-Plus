package org.dromara.system.domain.bo;

import org.dromara.system.domain.SysChatMessage;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 消息：用于记录聊天内容业务对象 sys_chat_message
 *
 * @author Tim
 * @date 2025-06-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysChatMessage.class, reverseConvertGenerate = false)
public class SysChatMessageBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 会话ID
     */
    @NotNull(message = "会话ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sessionId;

    /**
     * 发送者ID
     */
    @NotNull(message = "发送者ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long senderId;

    /**
     * 消息内容（支持文本、图片、文件等）
     */
    @NotBlank(message = "消息内容（支持文本、图片、文件等）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;

    /**
     * 消息类型（1=文本，2=图片，3=文件等）
     */
    @NotNull(message = "消息类型（1=文本，2=图片，3=文件等）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long messageType;


}
