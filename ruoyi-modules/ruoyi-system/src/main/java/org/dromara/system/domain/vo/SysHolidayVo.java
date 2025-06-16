package org.dromara.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.system.domain.HolidayQuotaRule;
import org.dromara.system.domain.SysHoliday;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 假期规则视图对象
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysHoliday.class)
public class SysHolidayVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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


    private List<HolidayQuotaRule> socialAgeRules;
    private List<HolidayQuotaRule> companyAgeRules;
}
