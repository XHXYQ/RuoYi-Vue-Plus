package org.dromara.system.mapper;

import org.apache.ibatis.annotations.Select;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.system.domain.SysEmployee;
import org.dromara.system.domain.vo.EmployeeStatsVo;
import org.dromara.system.domain.vo.SysEmployeeVo;

/**
 * 请假Mapper接口
 *
 * @author may
 * @date 2023-07-21
 */
public interface SysEmployeeMapper extends BaseMapperPlus<SysEmployee, SysEmployeeVo> {
    @Select("""
    SELECT
        COUNT(*) AS total,
        SUM(employee_type = '全职') AS fullTime,
        SUM(employee_type = '兼职') AS partTime,
        SUM(employee_type = '实习') AS intern,
        SUM(employee_type = '劳务派遣') AS dispatch,
        SUM(employee_type IS NULL OR employee_type = '') AS noType,
        SUM(status_type = '试用') AS probation,
        SUM(status_type = '正式') AS formal,
        SUM(status_type = '待离职') AS pendingLeave,
        SUM(status_type IS NULL OR status_type = '') AS noStatus
    FROM sys_employee
    WHERE status_type != '已离职'
""")
    EmployeeStatsVo selectEmployeeStats();

}
