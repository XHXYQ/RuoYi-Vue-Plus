package org.dromara.system.domain.bo;

import org.dromara.system.domain.SysOnboarding;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 员工入职申请业务对象 sys_onboarding
 *
 * @author Tim
 * @date 2025-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysOnboarding.class, reverseConvertGenerate = false)
public class SysOnboardingBo extends BaseEntity {

    /**
     * 入职记录ID
     */
    @NotNull(message = "入职记录ID不能为空", groups = { EditGroup.class })
    private Long onboardingId;

    /**
     * 用户ID
     */
//    @NotNull(message = "用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 入职部门ID
     */
    @NotNull(message = "入职部门ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long deptId;

    /**
     * 入职职位ID
     */
//    @NotNull(message = "入职职位ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long postId;

    /**
     * 预计入职日期（对应UI字段）
     */
    @NotNull(message = "预计入职日期（对应UI字段）不能为空", groups = { AddGroup.class, EditGroup.class })
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expectedDate;

    /**
     * 实际入职日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date contractStart;

    /**
     * 合同结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
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
