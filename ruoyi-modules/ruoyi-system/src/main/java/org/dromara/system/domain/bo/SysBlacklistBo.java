package org.dromara.system.domain.bo;

import org.dromara.system.domain.SysBlacklist;
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
 * 系统黑名单业务对象 sys_blacklist
 *
 * @author Tim
 * @date 2025-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysBlacklist.class, reverseConvertGenerate = false)
public class SysBlacklistBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String userName;

    /**
     * 证件号码
     */
    @NotBlank(message = "证件号码不能为空", groups = { AddGroup.class, EditGroup.class })
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
