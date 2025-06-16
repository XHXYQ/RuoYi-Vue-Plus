package org.dromara.system.domain.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.system.domain.SysTransfer;

import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysTransfer.class, reverseConvertGenerate = false)
public class SysTransferBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = {EditGroup.class})
    private Long id;

    @NotBlank(message = "姓名不能为空", groups = AddGroup.class)
    private String name;

    /**
     * 入职时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 原部门ID（关联sys_dept.dept_id）
     */
    @NotNull(message = "部门不能为空", groups = AddGroup.class)
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
    @NotNull(message = "部门不能为空", groups = AddGroup.class)
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

    private String deptName; // 添加部门名称字段

    /**
     * 调岗原因
     */
    private String remark;

    /**
     * 状态
     */
    private String status;

}
