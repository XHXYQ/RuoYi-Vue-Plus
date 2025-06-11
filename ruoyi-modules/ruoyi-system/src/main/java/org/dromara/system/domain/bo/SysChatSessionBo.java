package org.dromara.system.domain.bo;

import org.dromara.system.domain.SysChatSession;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 会话：用于记录会话（聊天房间），支持私聊或群聊业务对象 sys_chat_session
 *
 * @author Tim
 * @date 2025-06-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysChatSession.class, reverseConvertGenerate = false)
public class SysChatSessionBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 会话类型（1=私聊，2=群聊）
     */
    @NotNull(message = "会话类型（1=私聊，2=群聊）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sessionType;

    /**
     * 创建人用户ID
     */
    @NotNull(message = "创建人用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long creatorId;


}
