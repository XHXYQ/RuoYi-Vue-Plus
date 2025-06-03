package org.dromara.workflow.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.workflow.domain.TestRegularApplication;
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
 * 转正申请视图对象 test_regular_application
 *
 * @author Tim
 * @date 2025-05-24
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TestRegularApplication.class)
public class TestRegularApplicationVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 申请人姓名
     */
    @ExcelProperty(value = "申请人姓名")
    private String name;

    /**
     * 所在部门
     */
    @ExcelProperty(value = "所在部门")
    private String department;

    /**
     * 当前岗位
     */
    @ExcelProperty(value = "当前岗位")
    private String position;

    /**
     * 员工类型
     */
    @ExcelProperty(value = "员工类型")
    private String employeeType;

    /**
     * 入职日期
     */
    @ExcelProperty(value = "入职日期")
    private Date entryDate;

    /**
     * 试用期
     */
    @ExcelProperty(value = "试用期")
    private String probationPeriod;

    /**
     * 计划转正日期
     */
    @ExcelProperty(value = "计划转正日期")
    private Date planRegularDate;

    /**
     * 实际转正日期
     */
    @ExcelProperty(value = "实际转正日期")
    private Date actualRegularDate;

    /**
     * 审批状态（draft草稿, waiting待审, pass通过, reject退回）
     */
    @ExcelProperty(value = "审批状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "d=raft草稿,,w=aiting待审,,p=ass通过,,r=eject退回")
    private String status;

    /**
     * 关联的流程实例ID
     */
    @ExcelProperty(value = "关联的流程实例ID")
    private Long flowInstanceId;

    /**
     * 审批意见
     */
    @ExcelProperty(value = "审批意见")
    private String approvalOpinion;

    /**
     * 附件地址（可为空）
     */
    @ExcelProperty(value = "附件地址", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "可=为空")
    private String fileUrl;


}
