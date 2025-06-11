package org.dromara.system.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.dromara.system.domain.vo.SysChatMessageVo;
import org.dromara.system.domain.bo.SysChatMessageBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 消息：用于记录聊天内容Service接口
 *
 * @author Tim
 * @date 2025-06-09
 */
public interface ISysChatMessageService {

    /**
     * 查询消息：用于记录聊天内容
     *
     * @param id 主键
     * @return 消息：用于记录聊天内容
     */
    SysChatMessageVo queryById(Long id);

    /**
     * 分页查询消息：用于记录聊天内容列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息：用于记录聊天内容分页列表
     */
    TableDataInfo<SysChatMessageVo> queryPageList(SysChatMessageBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的消息：用于记录聊天内容列表
     *
     * @param bo 查询条件
     * @return 消息：用于记录聊天内容列表
     */
    List<SysChatMessageVo> queryList(SysChatMessageBo bo);

    /**
     * 新增消息：用于记录聊天内容
     *
     * @param bo 消息：用于记录聊天内容
     * @return 是否新增成功
     */
    Boolean insertByBo(SysChatMessageBo bo);

    /**
     * 修改消息：用于记录聊天内容
     *
     * @param bo 消息：用于记录聊天内容
     * @return 是否修改成功
     */
    Boolean updateByBo(SysChatMessageBo bo);

    /**
     * 校验并批量删除消息：用于记录聊天内容信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    boolean sendGroupMessage(@NotNull(message = "会话ID不能为空") Long sessionId, @NotBlank(message = "消息内容不能为空") String message);
}
