package org.dromara.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.system.domain.SysTransfer;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysTransfer.class)
public class SysTransferVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 员工姓名
     */
    @ExcelProperty("姓名")
    private String name;

    /**
     * 入职时间
     */
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("入职日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @ExcelProperty("原部门ID")
    private Long deptoldId; // 部门ID

    /**
     * 员工原职位
     */
    @ExcelProperty("原职位")
    private String position;

    /**
     * 员工职位
     */
    @ExcelProperty("原职级")
    private String positionRank;


    /**
     * 现部门ID（关联sys_dept.dept_id）
     */
    @ExcelProperty("现部门ID")
    private Long deptId;

    /**
     * 员工转入职位
     */
    @ExcelProperty("转入职位")
    private String positionNew;

    /**
     * 员工转入职级
     */
    @ExcelProperty("转入职级")
    private String positionRanknew;

    /**
     * 生效时间
     */
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("生效日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 部门名称（非数据库字段，用于显示）
     */
    @ExcelProperty("部门名称")
    private String deptName;

    /**
     * 备注
     */
    @ExcelProperty(value = "调岗原因")
    private String remark;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;


}
