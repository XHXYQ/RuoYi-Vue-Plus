package org.dromara.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.system.domain.SysReplacement;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysReplacement.class, reverseConvertGenerate = false)
public class SysReplacementBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 补卡时间
     */
    @NotNull(message = "补卡时间不能为空", groups = {AddGroup.class, EditGroup.class})
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date replacementDate;

    /**
     * 补卡原因
     */
    private String remark;

    /**
     * 状态
     */
    private String status;

    /**
     * 补卡时间
     */
    private Integer replacementDays;
}
