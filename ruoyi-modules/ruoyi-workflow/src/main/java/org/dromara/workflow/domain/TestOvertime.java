package org.dromara.workflow.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 请假对象 test_leave
 *
 * @author may
 * @date 2023-07-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("test_overtime")
public class TestOvertime extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 请假类型
     */
    private String leaveType;

    /**
     * 开始时间
     */
    @TableField("start_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    /**
     * 结束时间
     */
    @TableField("end_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    /**
     * 请假天数
     */
    private Double leaveDays;

    /**
     * 请假原因
     */
    private String remark;

    /**
     * 状态
     */
    private String status;

}

