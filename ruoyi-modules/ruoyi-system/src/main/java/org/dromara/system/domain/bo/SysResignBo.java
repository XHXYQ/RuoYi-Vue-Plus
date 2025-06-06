package org.dromara.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.system.domain.SysResign;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysResign.class, reverseConvertGenerate = false)
public class SysResignBo extends BaseEntity {
    private Long userId;

    @NotBlank(message = "姓名不能为空", groups = AddGroup.class)
    private String name;

    @NotNull(message = "部门不能为空", groups = AddGroup.class)
    private Long deptId;

    private String deptName;

    private String position;

    private String employeeType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String remark;

    /**
     * 状态
     */
    private String status;

}
