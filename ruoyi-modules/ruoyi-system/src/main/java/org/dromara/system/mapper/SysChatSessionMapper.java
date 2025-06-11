package org.dromara.system.mapper;

import org.dromara.system.domain.SysChatSession;
import org.dromara.system.domain.vo.SysChatSessionVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 会话：用于记录会话（聊天房间），支持私聊或群聊Mapper接口
 *
 * @author Tim
 * @date 2025-06-09
 */
public interface SysChatSessionMapper extends BaseMapperPlus<SysChatSession, SysChatSessionVo> {

}
