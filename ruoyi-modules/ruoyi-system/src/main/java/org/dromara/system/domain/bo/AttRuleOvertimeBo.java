package org.dromara.system.domain.bo;

import org.dromara.system.domain.AttRuleOvertime;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
//import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 加班规则业务对象 att_rule_overtime
 *
 * @author Skye
 * @date 2025-06-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
//@AutoMapper(target = AttRuleOvertime.class, reverseConvertGenerate = false)
public class AttRuleOvertimeBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 应用范围（考勤组id）
     */
    private List<Long> groupsId;

    /**
     * 加班规则名称
     */
    @NotBlank(message = "加班规则名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 负责人id
     */
    @NotNull(message = "负责人id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long director;

    /**
     * 加班配置详情
     */
//    @NotBlank(message = "加班配置详情不能为空", groups = { AddGroup.class, EditGroup.class })
    private Map<String, Object> detail;

    /**
     * 加班时长单位 1小时 2天
     */
    @NotNull(message = "加班时长单位 1小时 2天不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long durationUnit;

    /**
     * 加班时长取整规则1不取整 2向下取整 3向上取整
     */
    @NotNull(message = "加班时长取整规则1不取整 2向下取整 3向上取整不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long toIntegerRule;

    /**
     * 取整最小单位
     */
    private Long toInterValue;

    /**
     * 日折算时长
     */
    @NotBlank(message = "日折算时长不能为空", groups = { AddGroup.class, EditGroup.class })
    private String hourToDay;

    /**
     * 有无风险预警数据  1有 0没有
     */
    @NotNull(message = "有无风险预警数据  1有 0没有不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long riskWarningStatus;

    /**
     * 风险预警
     */
    private List<Map<String, Object>> riskWarning;  // 改为 List<Map>

    /**
     * 有无最大加班时间数据  1有 0没有
     */
    @NotNull(message = "有无最大加班时间数据  1有 0没有不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long bigDurationTimeStatus;

    /**
     * 最大加班时间
     */
    private List<Map<String, Object>> bigDurationTime;  // 改为 List<Map>

    /**
     * 开始和结束都需要打卡按钮  1开 0关
     */
    @NotNull(message = "开始和结束都需要打卡按钮  1开 0关不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long startOrEndStatus;

    /**
     * 开始和结束都需要打卡
     */
    private Map<String, Object> startOrEnd;  // 改为 Map<String, Object>

    /**
     * 状态1 启用 0关闭
     */
//    @NotNull(message = "状态1 启用 0关闭不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long status;



}
