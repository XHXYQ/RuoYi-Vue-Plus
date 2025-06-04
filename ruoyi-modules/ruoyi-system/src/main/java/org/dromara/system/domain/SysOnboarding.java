package org.dromara.system.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 员工入职申请对象 sys_onboarding
 *
 * @author Tim
 * @date 2025-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_onboarding")
public class SysOnboarding extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入职记录ID
     */
    @TableId(value = "onboarding_id")
    private Long onboardingId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 入职部门ID
     */
    private Long deptId;

    /**
     * 入职职位ID
     */
    private Long postId;

    /**
     * 预计入职日期（对应UI字段）
     */
    private Date expectedDate;

    /**
     * 实际入职日期
     */
    private Date actualDate;

    /**
     * 试用期（月）
     */
    private Long probationMonths;

    /**
     * 基本薪资
     */
    private Long salary;

    /**
     * 合同开始日期
     */
    private Date contractStart;

    /**
     * 合同结束日期
     */
    private Date contractEnd;

    /**
     * 招聘来源（对应UI字段）
     */
    private String source;

    /**
     * 状态（0:待处理 1:已入职 2:已取消）
     */
    private Long status;

    /**
     * 背调认证状态（0未完成 1已完成）
     */
    private Long certificationFlag;

    /**
     * 入职登记表文件路径
     */
    private String registrationForm;

    /**
     * 备注
     */
    private String remark;


}
