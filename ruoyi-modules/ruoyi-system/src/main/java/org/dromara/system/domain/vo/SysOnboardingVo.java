package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.SysOnboarding;
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
 * 员工入职申请视图对象 sys_onboarding
 *
 * @author Tim
 * @date 2025-06-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysOnboarding.class)
public class SysOnboardingVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入职记录ID
     */
    @ExcelProperty(value = "入职记录ID")
    private Long onboardingId;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String userName;

    /**
     * 入职部门ID
     */
    @ExcelProperty(value = "入职部门ID")
    private Long deptId;

    /**
     * 入职职位ID
     */
    @ExcelProperty(value = "入职职位ID")
    private Long postId;

    /**
     * 预计入职日期（对应UI字段）
     */
    @ExcelProperty(value = "预计入职日期", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "对=应UI字段")
    private Date expectedDate;

    /**
     * 实际入职日期
     */
    @ExcelProperty(value = "实际入职日期")
    private Date actualDate;

    /**
     * 试用期（月）
     */
    @ExcelProperty(value = "试用期", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "月=")
    private Long probationMonths;

    /**
     * 基本薪资
     */
    @ExcelProperty(value = "基本薪资")
    private Long salary;

    /**
     * 合同开始日期
     */
    @ExcelProperty(value = "合同开始日期")
    private Date contractStart;

    /**
     * 合同结束日期
     */
    @ExcelProperty(value = "合同结束日期")
    private Date contractEnd;

    /**
     * 招聘来源（对应UI字段）
     */
    @ExcelProperty(value = "招聘来源", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "对=应UI字段")
    private String source;

    /**
     * 状态（0:待处理 1:已入职 2:已取消）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=:待处理,1=:已入职,2=:已取消")
    private Long status;

    /**
     * 背调认证状态（0未完成 1已完成）
     */
    @ExcelProperty(value = "背调认证状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=未完成,1=已完成")
    private Long certificationFlag;

    /**
     * 入职登记表文件路径
     */
    @ExcelProperty(value = "入职登记表文件路径")
    private String registrationForm;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
