package org.dromara.system.domain.bo;

import org.dromara.system.domain.SysSolicitudeRule;
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
 * 员工关怀规则配置业务对象 sys_solicitude_rule
 *
 * @author Tim
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysSolicitudeRule.class, reverseConvertGenerate = false)
public class SysSolicitudeRuleBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 关怀类型ID（关联 sys_solicitude_category）
     */
    @NotNull(message = "关怀类型ID（关联 sys_solicitude_category）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long categoryId;

    /**
     * 系统祝福是否启用（1=启用，0=关闭）
     */
    private Long enableSystemRemind;

    /**
     * 全员祝福是否启用（1=启用，0=关闭）
     */
    private Long enableAllRemind;

    /**
     * 提醒给同事（1=启用，0=关闭）
     */
    private Long enableColleagueRemind;

    /**
     * 提前几天提醒（默认0为当天）
     */
    private Long remindDaysBefore;

    /**
     * 提醒时间点
     */
    private Date remindTime;

    /**
     * 祝福模板内容
     */
    private String messageTemplate;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 删除时间（软删）
     */
    private Date deletedAt;


}
