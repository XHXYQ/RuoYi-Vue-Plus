package org.dromara.system.domain.bo;

import org.dromara.system.domain.SysCompanyManagement;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 公司组织管理业务对象 sys_company_management
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysCompanyManagement.class, reverseConvertGenerate = false)
public class SysCompanyManagementBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 唯一业务编号（如COMP-001）
     */
    @NotBlank(message = "唯一业务编号（如COMP-001）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * 部门/团队名称
     */
    @NotBlank(message = "部门/团队名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 成员人数（非负数）
     */
    private Long employeeCount;

    /**
     * 类型（如：部门/项目组/分公司）
     */
    private String type;

    /**
     * 主管姓名
     */
    private String manager;


}
