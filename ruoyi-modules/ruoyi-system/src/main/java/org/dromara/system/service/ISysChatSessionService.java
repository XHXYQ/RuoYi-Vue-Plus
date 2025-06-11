package org.dromara.system.service;

import org.dromara.system.domain.vo.SysChatSessionVo;
import org.dromara.system.domain.bo.SysChatSessionBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 会话：用于记录会话（聊天房间），支持私聊或群聊Service接口
 *
 * @author Tim
 * @date 2025-06-09
 */
public interface ISysChatSessionService {

    /**
     * 查询会话：用于记录会话（聊天房间），支持私聊或群聊
     *
     * @param id 主键
     * @return 会话：用于记录会话（聊天房间），支持私聊或群聊
     */
    SysChatSessionVo queryById(Long id);

    /**
     * 分页查询会话：用于记录会话（聊天房间），支持私聊或群聊列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 会话：用于记录会话（聊天房间），支持私聊或群聊分页列表
     */
    TableDataInfo<SysChatSessionVo> queryPageList(SysChatSessionBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的会话：用于记录会话（聊天房间），支持私聊或群聊列表
     *
     * @param bo 查询条件
     * @return 会话：用于记录会话（聊天房间），支持私聊或群聊列表
     */
    List<SysChatSessionVo> queryList(SysChatSessionBo bo);

    /**
     * 新增会话：用于记录会话（聊天房间），支持私聊或群聊
     *
     * @param bo 会话：用于记录会话（聊天房间），支持私聊或群聊
     * @return 是否新增成功
     */
    Boolean insertByBo(SysChatSessionBo bo);

    /**
     * 修改会话：用于记录会话（聊天房间），支持私聊或群聊
     *
     * @param bo 会话：用于记录会话（聊天房间），支持私聊或群聊
     * @return 是否修改成功
     */
    Boolean updateByBo(SysChatSessionBo bo);

    /**
     * 校验并批量删除会话：用于记录会话（聊天房间），支持私聊或群聊信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
