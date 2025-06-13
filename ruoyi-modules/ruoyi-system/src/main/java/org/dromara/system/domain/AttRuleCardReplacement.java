package org.dromara.system.domain;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 补卡规则对象 att_rule_card_replacement
 *
 * @author Skye
 * @date 2025-06-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("att_rule_card_replacement")
public class AttRuleCardReplacement extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 补卡规则名称
     */
    private String name;

    /**
     * 应用范围（考勤组id）
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String groupsId;

    /**
     * 负责人id数组
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String director;

    /**
     * 是否允许补卡 1允许 2不允许
     */
    private Long allow;

    /**
     * 每月可以交数量
     */
    private Long num;

    /**
     * 每月几号重算
     */
    private String date;

    /**
     * 可申请过去多少天的补卡
     */
    private Long pastDay;

    /**
     * 1工作日  2自然日
     */
    private Long dayType;

    /**
     * 补卡类型 1缺卡 2迟到 3早退 4正常
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private String type;

    /**
     *
     */
    private Date deletedAt;

    /**
     * 是否默认 1是 0否
     */
    private Long isDefault;


}
