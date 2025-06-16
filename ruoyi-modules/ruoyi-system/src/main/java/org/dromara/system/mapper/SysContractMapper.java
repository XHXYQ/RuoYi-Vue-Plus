package org.dromara.system.mapper;

import org.apache.ibatis.annotations.Select;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.system.domain.SysContract;
import org.dromara.system.domain.SysEmployee;
import org.dromara.system.domain.vo.EmployeeStatsVo;
import org.dromara.system.domain.vo.SysContractVo;
import org.dromara.system.domain.vo.SysEmployeeVo;

/**
 * 请假Mapper接口
 *
 * @author may
 * @date 2023-07-21
 */
public interface SysContractMapper extends BaseMapperPlus<SysContract, SysContractVo> {


}
