package org.dromara.workflow.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 外出申请对象 test_outgoing
 *
 * @author Tim
 * @date 2025-05-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("test_outgoing")
public class TestOutgoing extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 申请人
     */
    private String applicant;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 外出天数
     */
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
