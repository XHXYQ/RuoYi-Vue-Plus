package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 员工关怀规则配置对象 sys_solicitude_rule
 *
 * @author Tim
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_solicitude_rule")
public class SysSolicitudeRule extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 关怀类型ID（关联 sys_solicitude_category）
     */
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
