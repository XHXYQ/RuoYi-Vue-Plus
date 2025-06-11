package org.dromara.system.domain.bo;

import org.dromara.system.domain.SysChatSessionUser;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 会话参与人：记录会话中参与的用户，支持多用户加入群聊业务对象 sys_chat_session_user
 *
 * @author Tim
 * @date 2025-06-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysChatSessionUser.class, reverseConvertGenerate = false)
public class SysChatSessionUserBo extends BaseEntity {

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
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 加入时间
     */
    @NotNull(message = "加入时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date joinTime;

    /**
     * 是否被禁言（0否 1是）
     */
    @NotNull(message = "是否被禁言（0否 1是）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long isMuted;


}
