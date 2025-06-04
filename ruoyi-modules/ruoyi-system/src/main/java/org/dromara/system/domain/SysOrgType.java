package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 组织类型对象 sys_org_type
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_org_type")
public class SysOrgType extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 编号
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;


}
