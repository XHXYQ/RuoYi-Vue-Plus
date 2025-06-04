package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 公司组织管理对象 sys_company_management
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_company_management")
public class SysCompanyManagement extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 唯一业务编号（如COMP-001）
     */
    private String code;

    /**
     * 部门/团队名称
     */
    private String name;

    /**
     * 成员人数（非负数）
     */
    private Long employeeCount;

    /**
     * 类型（如：部门/项目组/分公司）
     */
    private String type;

    /**
     * 主管姓名
     */
    private String manager;


}
