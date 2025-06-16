package org.dromara.system.domain.vo;

import lombok.Data;

/**
 * 员工统计视图对象
 */
@Data
public class EmployeeStatsVo {
    private Integer total;
    private Integer fullTime;
    private Integer partTime;
    private Integer intern;
    private Integer dispatch;
    private Integer noType;
    private Integer probation;
    private Integer formal;
    private Integer pendingLeave;
    private Integer noStatus;
}
