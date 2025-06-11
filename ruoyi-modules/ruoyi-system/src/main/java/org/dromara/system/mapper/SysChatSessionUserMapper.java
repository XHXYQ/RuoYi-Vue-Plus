package org.dromara.system.mapper;

import org.dromara.system.domain.SysChatSessionUser;
import org.dromara.system.domain.vo.SysChatSessionUserVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 会话参与人：记录会话中参与的用户，支持多用户加入群聊Mapper接口
 *
 * @author Tim
 * @date 2025-06-09
 */
public interface SysChatSessionUserMapper extends BaseMapperPlus<SysChatSessionUser, SysChatSessionUserVo> {

}
