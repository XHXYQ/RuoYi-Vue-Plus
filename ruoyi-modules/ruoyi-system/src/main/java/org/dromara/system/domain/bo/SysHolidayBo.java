package org.dromara.system.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.system.domain.HolidayQuotaRule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.dromara.system.domain.SysHoliday;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysHoliday.class, reverseConvertGenerate = false)
public class SysHolidayBo extends BaseEntity {
    // 主键ID
    private Long holidayId;

    // 基础信息
    @NotBlank(message = "假期名称不能为空")
    @Size(max = 50, message = "假期名称长度不能超过50个字符")
    private String name;

    private String balanceType; // 余额规则（每月/每年/手动/加班计入）

    @Min(value = 1, message = "每月发放日期必须大于0")
    @Max(value = 31, message = "每月发放日期不能超过31")
    private Integer monthlyIssueDate;

    private String monthlyQuotaRule;

    @Min(value = 0, message = "每月额度最小为0")
    @Max(value = 365, message = "每月额度最大为365")
    private Integer monthlyQuota;

    private String monthlyValidity;

    private String yearlyIssueDate;
    private String yearlyQuotaRule;

    @Min(value = 0, message = "每年额度最小为0")
    @Max(value = 365, message = "每年额度最大为365")
    private Integer yearlyQuota;

    private String yearlyValidity;
    private Boolean allowExtendYearly;

    @Min(value = 0, message = "保留时长不能为负数")
    @Max(value = 365, message = "保留时长最大为365")
    private Integer retainValue;

    private String retainUnit;

    private String manualValidity;
    private Boolean allowActualWorkdays;

    @NotBlank(message = "新员工请假规则不能为空")
    private String newType;

    @NotBlank(message = "是否带薪不能为空")
    private String moneyType;

    @NotBlank(message = "请假单位不能为空")
    private String holidayType;

    @NotBlank(message = "计算方式不能为空")
    private String timeType;

    @NotBlank(message = "适用范围不能为空")
    private String scopeType;

    private Boolean expireReminder;
    private String remindTime;
    private Boolean notifyEmployee;
    private Boolean notifyManager;
    private String roundingUnit;
    private String holidaytimeType;

    // 规则列表
    @JsonIgnore
    private List<HolidayQuotaRule> socialAgeRules;

    @JsonIgnore
    private List<HolidayQuotaRule> companyAgeRules;

    private List<Long> selectedUserIds;
    private List<String> selectedUserNickNames;
}
