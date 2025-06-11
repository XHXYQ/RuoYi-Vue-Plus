package org.dromara.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.SpringUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.common.websocket.dto.WebSocketMessageDto;
import org.dromara.common.websocket.utils.WebSocketUtils;
import org.dromara.system.domain.SysChatSessionUser;
import org.dromara.system.mapper.SysChatSessionUserMapper;
import org.springframework.stereotype.Service;
import org.dromara.system.domain.bo.SysChatMessageBo;
import org.dromara.system.domain.vo.SysChatMessageVo;
import org.dromara.system.domain.SysChatMessage;
import org.dromara.system.mapper.SysChatMessageMapper;
import org.dromara.system.service.ISysChatMessageService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 消息：用于记录聊天内容Service业务层处理
 *
 * @author Tim
 * @date 2025-06-09
 */
@RequiredArgsConstructor
@Service
public class SysChatMessageServiceImpl implements ISysChatMessageService {

    private final SysChatMessageMapper baseMapper;

    /**
     * 查询消息：用于记录聊天内容
     *
     * @param id 主键
     * @return 消息：用于记录聊天内容
     */
    @Override
    public SysChatMessageVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询消息：用于记录聊天内容列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 消息：用于记录聊天内容分页列表
     */
    @Override
    public TableDataInfo<SysChatMessageVo> queryPageList(SysChatMessageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysChatMessage> lqw = buildQueryWrapper(bo);
        Page<SysChatMessageVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的消息：用于记录聊天内容列表
     *
     * @param bo 查询条件
     * @return 消息：用于记录聊天内容列表
     */
    @Override
    public List<SysChatMessageVo> queryList(SysChatMessageBo bo) {
        LambdaQueryWrapper<SysChatMessage> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysChatMessage> buildQueryWrapper(SysChatMessageBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysChatMessage> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SysChatMessage::getId);
        lqw.eq(bo.getSessionId() != null, SysChatMessage::getSessionId, bo.getSessionId());
        lqw.eq(bo.getSenderId() != null, SysChatMessage::getSenderId, bo.getSenderId());
        lqw.eq(StringUtils.isNotBlank(bo.getContent()), SysChatMessage::getContent, bo.getContent());
        lqw.eq(bo.getMessageType() != null, SysChatMessage::getMessageType, bo.getMessageType());
        return lqw;
    }

    /**
     * 新增消息：用于记录聊天内容
     *
     * @param bo 消息：用于记录聊天内容
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SysChatMessageBo bo) {
        SysChatMessage add = MapstructUtils.convert(bo, SysChatMessage.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改消息：用于记录聊天内容
     *
     * @param bo 消息：用于记录聊天内容
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SysChatMessageBo bo) {
        SysChatMessage update = MapstructUtils.convert(bo, SysChatMessage.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysChatMessage entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除消息：用于记录聊天内容信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }

    /**
     * 发送群聊消息（带推送）
     *
     * @param sessionId 聊天室 ID
     * @param content   消息内容
     * @return 是否发送成功
     */
    public boolean sendGroupMessage(Long sessionId, String content) {
        Long senderId = StpUtil.getLoginIdAsLong();

        // 1. 保存消息
        SysChatMessage message = new SysChatMessage();
        message.setSessionId(sessionId);
        message.setSenderId(senderId);
        message.setContent(content);
        message.setSendTime(LocalDateTime.now());
        baseMapper.insert(message);

        // 2. 查询接收人列表（群成员）
        List<Long> userIds = SpringUtils.getBean(SysChatSessionUserMapper.class)
            .selectList(new LambdaQueryWrapper<SysChatSessionUser>()
                .eq(SysChatSessionUser::getSessionId, sessionId))
            .stream().map(SysChatSessionUser::getUserId).toList();

        // 3. 构造结构化消息内容
        Map<String, Object> messagePayload = Map.of(
            "type", "chat",
            "sessionId", sessionId,
            "senderId", senderId,
//            "senderName", StpUtil.getLoginUser().getUsername(), // 你有 LoginUser 就能获取用户名
            "content", content,
//            "sendTime", message.getSendTime().toString(),
            "messageId", message.getId()
        );

        // 4. 推送消息
        WebSocketMessageDto dto = new WebSocketMessageDto();
        dto.setSessionKeys(userIds); // 接收人列表
        dto.setMessage(JSONUtil.toJsonStr(messagePayload)); // 推荐使用 Hutool 的 JSONUtil
        WebSocketUtils.publishMessage(dto);

        return true;
    }


}
