package org.dromara.system.service;

import org.dromara.system.domain.vo.SysChatSessionUserVo;
import org.dromara.system.domain.bo.SysChatSessionUserBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 会话参与人：记录会话中参与的用户，支持多用户加入群聊Service接口
 *
 * @author Tim
 * @date 2025-06-09
 */
public interface ISysChatSessionUserService {

    /**
     * 查询会话参与人：记录会话中参与的用户，支持多用户加入群聊
     *
     * @param id 主键
     * @return 会话参与人：记录会话中参与的用户，支持多用户加入群聊
     */
    SysChatSessionUserVo queryById(Long id);

    /**
     * 分页查询会话参与人：记录会话中参与的用户，支持多用户加入群聊列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 会话参与人：记录会话中参与的用户，支持多用户加入群聊分页列表
     */
    TableDataInfo<SysChatSessionUserVo> queryPageList(SysChatSessionUserBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的会话参与人：记录会话中参与的用户，支持多用户加入群聊列表
     *
     * @param bo 查询条件
     * @return 会话参与人：记录会话中参与的用户，支持多用户加入群聊列表
     */
    List<SysChatSessionUserVo> queryList(SysChatSessionUserBo bo);

    /**
     * 新增会话参与人：记录会话中参与的用户，支持多用户加入群聊
     *
     * @param bo 会话参与人：记录会话中参与的用户，支持多用户加入群聊
     * @return 是否新增成功
     */
    Boolean insertByBo(SysChatSessionUserBo bo);

    /**
     * 修改会话参与人：记录会话中参与的用户，支持多用户加入群聊
     *
     * @param bo 会话参与人：记录会话中参与的用户，支持多用户加入群聊
     * @return 是否修改成功
     */
    Boolean updateByBo(SysChatSessionUserBo bo);

    /**
     * 校验并批量删除会话参与人：记录会话中参与的用户，支持多用户加入群聊信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
