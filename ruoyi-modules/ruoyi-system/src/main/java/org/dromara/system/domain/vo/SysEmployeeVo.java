package org.dromara.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
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
@AutoMapper(target = SysEmployee.class)
public class SysEmployeeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty("员工ID")
    private Long userId;

    /**
     * 员工姓名
     */
    @ExcelProperty("姓名")
    private String name;

    /**
     * 手机号码
     */
    @ExcelProperty("手机号")
    private String phonenumber;

    @ExcelProperty("部门ID")
    private Long deptId; // 部门ID

    @ExcelProperty("部门名称")
    private String deptName; // 部门名称

    /**
     * 员工职位
     */
    @ExcelProperty("职位")
    private String position;

    /**
     * 员工类型
     */
    @ExcelProperty("员工类型")
    private String employeeType;

    /**
     * 员工状态
     */
    @ExcelProperty("状态")
    private String statusType;  // 保持与实体类一致

    /**
     * 入职时间
     */
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty("入职日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;


}

