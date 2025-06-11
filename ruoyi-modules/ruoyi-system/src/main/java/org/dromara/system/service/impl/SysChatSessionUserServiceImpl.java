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
import org.dromara.system.domain.bo.SysChatSessionUserBo;
import org.dromara.system.domain.vo.SysChatSessionUserVo;
import org.dromara.system.domain.SysChatSessionUser;
import org.dromara.system.mapper.SysChatSessionUserMapper;
import org.dromara.system.service.ISysChatSessionUserService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 会话参与人：记录会话中参与的用户，支持多用户加入群聊Service业务层处理
 *
 * @author Tim
 * @date 2025-06-09
 */
@RequiredArgsConstructor
@Service
public class SysChatSessionUserServiceImpl implements ISysChatSessionUserService {

    private final SysChatSessionUserMapper baseMapper;

    /**
     * 查询会话参与人：记录会话中参与的用户，支持多用户加入群聊
     *
     * @param id 主键
     * @return 会话参与人：记录会话中参与的用户，支持多用户加入群聊
     */
    @Override
    public SysChatSessionUserVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询会话参与人：记录会话中参与的用户，支持多用户加入群聊列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 会话参与人：记录会话中参与的用户，支持多用户加入群聊分页列表
     */
    @Override
    public TableDataInfo<SysChatSessionUserVo> queryPageList(SysChatSessionUserBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysChatSessionUser> lqw = buildQueryWrapper(bo);
        Page<SysChatSessionUserVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的会话参与人：记录会话中参与的用户，支持多用户加入群聊列表
     *
     * @param bo 查询条件
     * @return 会话参与人：记录会话中参与的用户，支持多用户加入群聊列表
     */
    @Override
    public List<SysChatSessionUserVo> queryList(SysChatSessionUserBo bo) {
        LambdaQueryWrapper<SysChatSessionUser> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysChatSessionUser> buildQueryWrapper(SysChatSessionUserBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysChatSessionUser> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SysChatSessionUser::getId);
        lqw.eq(bo.getSessionId() != null, SysChatSessionUser::getSessionId, bo.getSessionId());
        lqw.eq(bo.getUserId() != null, SysChatSessionUser::getUserId, bo.getUserId());
        lqw.eq(bo.getJoinTime() != null, SysChatSessionUser::getJoinTime, bo.getJoinTime());
        lqw.eq(bo.getIsMuted() != null, SysChatSessionUser::getIsMuted, bo.getIsMuted());
        return lqw;
    }

    /**
     * 新增会话参与人：记录会话中参与的用户，支持多用户加入群聊
     *
     * @param bo 会话参与人：记录会话中参与的用户，支持多用户加入群聊
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SysChatSessionUserBo bo) {
        SysChatSessionUser add = MapstructUtils.convert(bo, SysChatSessionUser.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改会话参与人：记录会话中参与的用户，支持多用户加入群聊
     *
     * @param bo 会话参与人：记录会话中参与的用户，支持多用户加入群聊
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SysChatSessionUserBo bo) {
        SysChatSessionUser update = MapstructUtils.convert(bo, SysChatSessionUser.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysChatSessionUser entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除会话参与人：记录会话中参与的用户，支持多用户加入群聊信息
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
