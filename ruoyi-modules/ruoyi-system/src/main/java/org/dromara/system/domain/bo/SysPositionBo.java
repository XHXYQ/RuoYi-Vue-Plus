package org.dromara.system.domain.bo;

import org.dromara.system.domain.SysPosition;
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
 * 系统职位管理业务对象 sys_position
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysPosition.class, reverseConvertGenerate = false)
public class SysPositionBo extends BaseEntity {

    /**
     * 自增主键ID
     */
    @NotNull(message = "自增主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 职位编号（如POS-001）
     */
    @NotBlank(message = "职位编号（如POS-001）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * 职位名称
     */
    @NotBlank(message = "职位名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 职位描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
