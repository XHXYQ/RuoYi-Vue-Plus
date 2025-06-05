package org.dromara.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_resign")
public class SysResign extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id")
    private Long userId;

    private String name;

    private Long deptId;

    @TableField(exist = false)
    private String deptName;

    private String position;

    private String employeeType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String remark;

}
