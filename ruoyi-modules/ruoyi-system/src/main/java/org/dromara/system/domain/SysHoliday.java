package org.dromara.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_holiday", autoResultMap = true)
public class SysHoliday extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "holiday_id")
    private Long holidayId;

    private String name;
    private String balanceType;

    // 每月发放相关
    private Integer monthlyIssueDate;
    private String monthlyQuotaRule;
    private Integer monthlyQuota;
    private String monthlyValidity;

    // 每年发放相关
    private String yearlyIssueDate;
    private String yearlyQuotaRule;
    private Integer yearlyQuota;
    private String yearlyValidity;
    private Boolean allowExtendYearly;
    private Integer retainValue;
    private String retainUnit;

    // 手动发放相关
    private String manualValidity;
    private Boolean allowActualWorkdays;
    private String newType;
    private String moneyType;
    private String holidayType;
    private String timeType;
    private String scopeType;
    private Boolean expireReminder;
    private String remindTime;
    private Boolean notifyEmployee;
    private Boolean notifyManager;
    private String roundingUnit;
    private String holidaytimeType;

    // 规则配置（JSON存储）
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<HolidayQuotaRule> socialAgeRules;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<HolidayQuotaRule> companyAgeRules;
}
