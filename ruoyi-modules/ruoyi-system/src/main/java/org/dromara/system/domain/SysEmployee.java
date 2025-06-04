package org.dromara.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import dm.jdbc.internal.desc.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_employee")
public class SysEmployee extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "user_id")
    private Long userId;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 所在部门ID（关联sys_dept.dept_id）
     */
    private Long deptId;

    /**
     * 部门名称（非数据库字段，用于显示）
     */
    @TableField(exist = false)
    private String deptName;

    /**
     * 员工职位
     */
    private String position;

    /**
     * 员工类型
     */
    private String employeeType;

    /**
     * 员工状态
     */
    private String statusType;

    /**
     * 入职时间
     */
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

