package org.dromara.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.xss.Xss;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import jakarta.validation.constraints.NotBlank;
import org.dromara.system.domain.SysEmployee;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysEmployee.class, reverseConvertGenerate = false)
public class SysEmployeeBo extends BaseEntity {

    private Long userId;

    @NotBlank(message = "姓名不能为空", groups = AddGroup.class)
    private String name;

    @NotBlank(message = "手机号不能为空", groups = AddGroup.class)
    private String phonenumber;

    @NotNull(message = "部门不能为空", groups = AddGroup.class)
    private Long deptId; // 改为部门ID

    private String deptName; // 添加部门名称字段

    private String position;

    private String employeeType;

    private String statusType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 用户账号
     */
    @Xss(message = "用户账号不能包含脚本字符")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过{max}个字符")
    private String userName;

    /**
     * 用户昵称
     */
    @Xss(message = "用户昵称不能包含脚本字符")
    @NotBlank(message = "用户昵称不能为空")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过{max}个字符")
    private String nickName;
}


