package org.dromara.workflow.domain.bo;

import org.dromara.workflow.domain.TestRegularApplication;
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
 * 转正申请业务对象 test_regular_application
 *
 * @author Tim
 * @date 2025-05-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TestRegularApplication.class, reverseConvertGenerate = false)
public class TestRegularApplicationBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 申请人姓名
     */
    @NotBlank(message = "申请人姓名不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;

    /**
     * 试用期
     */
    private String probationPeriod;

    /**
     * 计划转正日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date planRegularDate;

    /**
     * 实际转正日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
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


}
