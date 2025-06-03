package org.dromara.system.domain.bo;

import org.dromara.system.domain.SysOrgType;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 组织类型业务对象 sys_org_type
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysOrgType.class, reverseConvertGenerate = false)
public class SysOrgTypeBo extends BaseEntity {

    /**
     * 主键 ID
     */
    @NotNull(message = "主键 ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 编号
     */
    @NotBlank(message = "编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 描述
     */
    private String description;


}
