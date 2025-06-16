package org.dromara.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.system.domain.SysContract;
import org.dromara.system.domain.SysEmployee;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * 员工视图对象
 *
 * @author may
 * @date 2023-07-21
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysContract.class)
public class SysContractVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty("员工ID")
    private Long userId;


    @ExcelProperty("姓名")
    private String name;


    @ExcelProperty("工号")
    private String jobnumber;

    @ExcelProperty("部门ID")
    private Long deptId; // 部门ID

    @ExcelProperty("部门名称")
    private String deptName; // 部门名称

    @ExcelProperty("员工类型")
    private String employeeType;

    @ExcelProperty("合同编号")
    private String contractnumber;

    @ExcelProperty("合同类型")
    private String contractType;

    @ExcelProperty("签署状态")
    private String statusType;

    @ExcelProperty("合同期限")
    private String deadlineType;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("预计终止日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ExcelProperty("续签状态")
    private String statusxuType;

    @ExcelProperty("法人公司")
    private String company;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("签订日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signDate;


}

