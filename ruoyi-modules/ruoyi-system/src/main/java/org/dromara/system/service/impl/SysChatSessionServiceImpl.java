package org.dromara.system.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.system.domain.bo.SysChatSessionBo;
import org.dromara.system.domain.vo.SysChatSessionVo;
import org.dromara.system.domain.SysChatSession;
import org.dromara.system.mapper.SysChatSessionMapper;
import org.dromara.system.service.ISysChatSessionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 会话：用于记录会话（聊天房间），支持私聊或群聊Service业务层处理
 *
 * @author Tim
 * @date 2025-06-09
 */
@RequiredArgsConstructor
@Service
public class SysChatSessionServiceImpl implements ISysChatSessionService {

    private final SysChatSessionMapper baseMapper;

    /**
     * 查询会话：用于记录会话（聊天房间），支持私聊或群聊
     *
     * @param id 主键
     * @return 会话：用于记录会话（聊天房间），支持私聊或群聊
     */
    @Override
    public SysChatSessionVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询会话：用于记录会话（聊天房间），支持私聊或群聊列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 会话：用于记录会话（聊天房间），支持私聊或群聊分页列表
     */
    @Override
    public TableDataInfo<SysChatSessionVo> queryPageList(SysChatSessionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysChatSession> lqw = buildQueryWrapper(bo);
        Page<SysChatSessionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的会话：用于记录会话（聊天房间），支持私聊或群聊列表
     *
     * @param bo 查询条件
     * @return 会话：用于记录会话（聊天房间），支持私聊或群聊列表
     */
    @Override
    public List<SysChatSessionVo> queryList(SysChatSessionBo bo) {
        LambdaQueryWrapper<SysChatSession> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysChatSession> buildQueryWrapper(SysChatSessionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysChatSession> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SysChatSession::getId);
        lqw.eq(bo.getSessionType() != null, SysChatSession::getSessionType, bo.getSessionType());
        lqw.eq(bo.getCreatorId() != null, SysChatSession::getCreatorId, bo.getCreatorId());
        return lqw;
    }

    /**
     * 新增会话：用于记录会话（聊天房间），支持私聊或群聊
     *
     * @param bo 会话：用于记录会话（聊天房间），支持私聊或群聊
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SysChatSessionBo bo) {
        SysChatSession add = MapstructUtils.convert(bo, SysChatSession.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改会话：用于记录会话（聊天房间），支持私聊或群聊
     *
     * @param bo 会话：用于记录会话（聊天房间），支持私聊或群聊
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SysChatSessionBo bo) {
        SysChatSession update = MapstructUtils.convert(bo, SysChatSession.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysChatSession entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除会话：用于记录会话（聊天房间），支持私聊或群聊信息
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
}
