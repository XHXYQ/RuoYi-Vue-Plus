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
import org.dromara.system.domain.SysContract;
import org.dromara.system.domain.SysEmployee;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysContract.class, reverseConvertGenerate = false)
public class SysContractBo extends BaseEntity {

    private Long userId;

    @NotBlank(message = "姓名不能为空", groups = AddGroup.class)
    private String name;

    @NotBlank(message = "工作号不能为空", groups = AddGroup.class)
    private String jobnumber;

    @NotNull(message = "部门不能为空", groups = AddGroup.class)
    private Long deptId;

    private String deptName;

    private String employeeType;

    private String contractType;

    private String contractnumber;

    private String statusType;

    private String deadlineType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String statusxuType;

    private String company;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signDate;

}



