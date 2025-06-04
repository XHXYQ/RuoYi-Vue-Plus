package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 系统职位管理对象 sys_position
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_position")
public class SysPosition extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 职位编号（如POS-001）
     */
    private String code;

    /**
     * 职位名称
     */
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
