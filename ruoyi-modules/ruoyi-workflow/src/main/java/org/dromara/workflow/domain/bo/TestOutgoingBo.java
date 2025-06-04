package org.dromara.workflow.domain.bo;

import org.dromara.workflow.domain.TestOutgoing;
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
 * 外出申请业务对象 test_outgoing
 *
 * @author Tim
 * @date 2025-05-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TestOutgoing.class, reverseConvertGenerate = false)
public class TestOutgoingBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 申请人
     */
    @NotBlank(message = "申请人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String applicant;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date startTime;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date endTime;

    /**
     * 外出天数
     */
    @NotNull(message = "外出天数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long leaveDay;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private String status;


}
