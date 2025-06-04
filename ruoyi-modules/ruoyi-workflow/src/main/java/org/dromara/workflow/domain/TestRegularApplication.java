package org.dromara.workflow.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 转正申请对象 test_regular_application
 *
 * @author Tim
 * @date 2025-05-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("test_regular_application")
public class TestRegularApplication extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 申请人姓名
     */
    private String name;

    /**
     * 所在部门
     */
    private String department;

    /**
     * 当前岗位
     */
    private String position;

    /**
     * 员工类型
     */
    private String employeeType;

    /**
     * 入职日期
     */
    private Date entryDate;

    /**
     * 试用期
     */
    private String probationPeriod;

    /**
     * 计划转正日期
     */
    private Date planRegularDate;

    /**
     * 实际转正日期
     */
    private Date actualRegularDate;

    /**
     * 审批状态（draft草稿, waiting待审, pass通过, reject退回）
     */
    private String status;

    /**
     * 关联的流程实例ID
     */
    private Long flowInstanceId;

    /**
     * 审批意见
     */
    private String approvalOpinion;

    /**
     * 附件地址（可为空）
     */
    private String fileUrl;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private String delFlag;


}
