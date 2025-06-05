package org.dromara.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.system.domain.SysResign;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysResign.class)
public class SysResignVo implements Serializable {

    @Serial
    private static  final long serialVersionUID = 1L;

    @ExcelProperty("员工ID")
    private Long userId;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("部门ID")
    private Long deptId;

    @ExcelProperty("部门名称")
    private String deptName;

    @ExcelProperty("职位")
    private String position;

    @ExcelProperty("员工类型")
    private String employeeType;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("入职日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("离职日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ExcelProperty(value = "离职原因")
    private String remark;

}
