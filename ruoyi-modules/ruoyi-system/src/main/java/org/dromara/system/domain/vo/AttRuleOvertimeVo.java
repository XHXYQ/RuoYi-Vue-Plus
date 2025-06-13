package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.AttRuleOvertime;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 加班规则视图对象 att_rule_overtime
 *
 * @author Skye
 * @date 2025-06-13
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AttRuleOvertime.class)
public class AttRuleOvertimeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 应用范围（考勤组id）
     */
    @ExcelProperty(value = "应用范围", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "考=勤组id")
    private String groupsId;

    /**
     * 加班规则名称
     */
    @ExcelProperty(value = "加班规则名称")
    private String name;

    /**
     * 负责人id
     */
    @ExcelProperty(value = "负责人id")
    private Long director;

    /**
     * 加班配置详情
     */
    @ExcelProperty(value = "加班配置详情")
    private String detail;

    /**
     * 加班时长单位 1小时 2天
     */
    @ExcelProperty(value = "加班时长单位 1小时 2天")
    private Long durationUnit;

    /**
     * 加班时长取整规则1不取整 2向下取整 3向上取整
     */
    @ExcelProperty(value = "加班时长取整规则1不取整 2向下取整 3向上取整")
    private Long toIntegerRule;

    /**
     * 取整最小单位
     */
    @ExcelProperty(value = "取整最小单位")
    private Long toInterValue;

    /**
     * 日折算时长
     */
    @ExcelProperty(value = "日折算时长")
    private String hourToDay;

    /**
     * 有无风险预警数据  1有 0没有
     */
    @ExcelProperty(value = "有无风险预警数据  1有 0没有")
    private Long riskWarningStatus;

    /**
     * 风险预警
     */
    @ExcelProperty(value = "风险预警")
    private String riskWarning;

    /**
     * 有无最大加班时间数据  1有 0没有
     */
    @ExcelProperty(value = "有无最大加班时间数据  1有 0没有")
    private Long bigDurationTimeStatus;

    /**
     * 最大加班时间
     */
    @ExcelProperty(value = "最大加班时间")
    private String bigDurationTime;

    /**
     * 开始和结束都需要打卡按钮  1开 0关
     */
    @ExcelProperty(value = "开始和结束都需要打卡按钮  1开 0关")
    private Long startOrEndStatus;

    /**
     * 开始和结束都需要打卡
     */
    @ExcelProperty(value = "开始和结束都需要打卡")
    private String startOrEnd;

    /**
     * 状态1 启用 0关闭
     */
    @ExcelProperty(value = "状态1 启用 0关闭")
    private Long status;

    /**
     * 是否默认 1是 0否
     */
    @ExcelProperty(value = "是否默认 1是 0否")
    private Long isDefault;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Date deletedAt;


}
