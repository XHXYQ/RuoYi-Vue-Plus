package org.dromara.system.domain;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 加班规则对象 att_rule_overtime
 *
 * @author Skye
 * @date 2025-06-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("att_rule_overtime")
public class AttRuleOvertime extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 应用范围（考勤组id）
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String groupsId;

    /**
     * 加班规则名称
     */
    private String name;

    /**
     * 负责人id
     */
    private Long director;

    /**
     * 加班配置详情
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String detail;

    /**
     * 加班时长单位 1小时 2天
     */
    private Long durationUnit;

    /**
     * 加班时长取整规则1不取整 2向下取整 3向上取整
     */
    private Long toIntegerRule;

    /**
     * 取整最小单位
     */
    private Long toInterValue;

    /**
     * 日折算时长
     */
    private String hourToDay;

    /**
     * 有无风险预警数据  1有 0没有
     */
    private Long riskWarningStatus;

    /**
     * 风险预警
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String riskWarning;

    /**
     * 有无最大加班时间数据  1有 0没有
     */
    private Long bigDurationTimeStatus;

    /**
     * 最大加班时间
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String bigDurationTime;

    /**
     * 开始和结束都需要打卡按钮  1开 0关
     */
    private Long startOrEndStatus;

    /**
     * 开始和结束都需要打卡
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String startOrEnd;

    /**
     * 状态1 启用 0关闭
     */
    private Long status;

    /**
     * 是否默认 1是 0否
     */
    private Long isDefault;

    /**
     *
     */
    private Date deletedAt;


}
