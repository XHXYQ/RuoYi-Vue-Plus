package org.dromara.system.mapper;

import org.apache.ibatis.annotations.*;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.system.domain.SysHoliday;
import org.dromara.system.domain.vo.SysHolidayVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;


import java.util.List;

/**
 * 假期规则Mapper接口
 */
public interface SysHolidayMapper extends BaseMapperPlus<SysHoliday, SysHolidayVo> {


}
