package org.dromara.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_transfer")
public class SysTransfer extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 入职时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 原部门ID（关联sys_dept.dept_id）
     */
    private Long deptoldId;

    /**
     * 员工原职位
     */
    private String position;

    /**
     * 员工职位
     */
    private String positionRank;


    /**
     * 现部门ID（关联sys_dept.dept_id）
     */
    private Long deptId;

    /**
     * 员工转入职位
     */
    private String positionNew;

    /**
     * 员工转入职位
     */
    private String positionRanknew;

    /**
     * 生效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 部门名称（非数据库字段，用于显示）
     */
    @TableField(exist = false)
    private String deptName;

    /**
     * 调岗原因
     */
    private String remark;

    /**
     * 状态
     */
    private String status;
}

