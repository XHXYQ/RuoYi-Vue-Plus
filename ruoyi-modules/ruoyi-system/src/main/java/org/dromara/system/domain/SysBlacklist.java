package org.dromara.system.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 系统黑名单对象 sys_blacklist
 *
 * @author Tim
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_blacklist")
public class SysBlacklist extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 证件号码
     */
    private String idNumber;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 加黑原因
     */
    private String reason;

    /**
     * 加黑时间
     */
    private Date blacklistedAt;


}
