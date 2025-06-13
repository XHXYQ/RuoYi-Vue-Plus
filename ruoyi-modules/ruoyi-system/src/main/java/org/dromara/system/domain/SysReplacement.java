package org.dromara.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_replacement")
public class SysReplacement extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 补卡时间
     */
    private Date replacementDate;

    /**
     * 补卡原因
     */
    private String remark;

    /**
     * 状态
     */
    private String status;
}
