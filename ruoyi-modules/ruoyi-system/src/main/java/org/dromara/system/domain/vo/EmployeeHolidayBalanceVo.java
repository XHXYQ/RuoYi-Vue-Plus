package org.dromara.system.domain.vo;

import lombok.Data;

@Data
public class EmployeeHolidayBalanceVo {
    private Long holidayId;
    private String holidayName;
    private String balanceType;
    // 可以根据需要添加更多字段
}
