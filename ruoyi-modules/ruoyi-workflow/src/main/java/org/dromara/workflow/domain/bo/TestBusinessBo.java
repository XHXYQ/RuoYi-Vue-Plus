package org.dromara.workflow.domain.bo;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.domain.event.ProcessCreateTaskEvent;
import org.dromara.common.core.domain.event.ProcessEvent;
import org.dromara.common.core.enums.BusinessStatusEnum;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.workflow.domain.TestBusiness;
import org.springframework.context.event.EventListener;

import java.util.Date;
import java.util.Map;

/**
 * 出差业务对象 test_leave
 *
 * @author may
 * @date 2023-07-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TestBusiness.class, reverseConvertGenerate = false)
public class TestBusinessBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 出差类型
     */
    @NotBlank(message = "请假类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private String leaveType;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空", groups = {AddGroup.class, EditGroup.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空", groups = {AddGroup.class, EditGroup.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 出差天数
     */
    private Integer leaveDays;

    /**
     * 开始时间
     */
    private Integer startLeaveDays;

    /**
     * 结束时间
     */
    private Integer endLeaveDays;

    /**
     * 出差原因
     */
    private String remark;

    /**
     * 状态
     */
    private String status;


}
