package org.dromara.system.domain.bo;

import org.dromara.system.domain.AttRuleCardReplacement;
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
 * 补卡规则业务对象 att_rule_card_replacement
 *
 * @author Skye
 * @date 2025-06-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AttRuleCardReplacement.class, reverseConvertGenerate = false)
public class AttRuleCardReplacementBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 补卡规则名称
     */
    @NotBlank(message = "补卡规则名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 应用范围（考勤组id）
     */
    private String groupsId;

    /**
     * 负责人id数组
     */
    @NotBlank(message = "负责人id数组不能为空", groups = { AddGroup.class, EditGroup.class })
    private String director;

    /**
     * 是否允许补卡 1允许 2不允许
     */
    @NotNull(message = "是否允许补卡 1允许 2不允许不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long allow;

    /**
     * 每月可以交数量
     */
    @NotNull(message = "每月可以交数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long num;

    /**
     * 每月几号重算
     */
    @NotBlank(message = "每月几号重算不能为空", groups = { AddGroup.class, EditGroup.class })
    private String date;

    /**
     * 可申请过去多少天的补卡
     */
    @NotNull(message = "可申请过去多少天的补卡不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long pastDay;

    /**
     * 1工作日  2自然日
     */
    @NotNull(message = "1工作日  2自然日不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long dayType;

    /**
     * 补卡类型 1缺卡 2迟到 3早退 4正常
     */
    @NotBlank(message = "补卡类型 1缺卡 2迟到 3早退 4正常不能为空", groups = { AddGroup.class, EditGroup.class })
    private String type;

    /**
     *
     */
    private Date deletedAt;

    /**
     * 是否默认 1是 0否
     */
    @NotNull(message = "是否默认 1是 0否不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long isDefault;


}
