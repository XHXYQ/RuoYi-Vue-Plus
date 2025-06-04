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
import org.dromara.system.domain.bo.SysBlacklistBo;
import org.dromara.system.domain.vo.SysBlacklistVo;
import org.dromara.system.domain.SysBlacklist;
import org.dromara.system.mapper.SysBlacklistMapper;
import org.dromara.system.service.ISysBlacklistService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 系统黑名单Service业务层处理
 *
 * @author Tim
 * @date 2025-06-04
 */
@RequiredArgsConstructor
@Service
public class SysBlacklistServiceImpl implements ISysBlacklistService {

    private final SysBlacklistMapper baseMapper;

    /**
     * 查询系统黑名单
     *
     * @param id 主键
     * @return 系统黑名单
     */
    @Override
    public SysBlacklistVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询系统黑名单列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 系统黑名单分页列表
     */
    @Override
    public TableDataInfo<SysBlacklistVo> queryPageList(SysBlacklistBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysBlacklist> lqw = buildQueryWrapper(bo);
        Page<SysBlacklistVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的系统黑名单列表
     *
     * @param bo 查询条件
     * @return 系统黑名单列表
     */
    @Override
    public List<SysBlacklistVo> queryList(SysBlacklistBo bo) {
        LambdaQueryWrapper<SysBlacklist> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysBlacklist> buildQueryWrapper(SysBlacklistBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysBlacklist> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SysBlacklist::getId);
        lqw.like(StringUtils.isNotBlank(bo.getUserName()), SysBlacklist::getUserName, bo.getUserName());
        lqw.eq(StringUtils.isNotBlank(bo.getIdNumber()), SysBlacklist::getIdNumber, bo.getIdNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getPhonenumber()), SysBlacklist::getPhonenumber, bo.getPhonenumber());
        lqw.eq(StringUtils.isNotBlank(bo.getReason()), SysBlacklist::getReason, bo.getReason());
        lqw.eq(bo.getBlacklistedAt() != null, SysBlacklist::getBlacklistedAt, bo.getBlacklistedAt());
        return lqw;
    }

    /**
     * 新增系统黑名单
     *
     * @param bo 系统黑名单
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SysBlacklistBo bo) {
        SysBlacklist add = MapstructUtils.convert(bo, SysBlacklist.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改系统黑名单
     *
     * @param bo 系统黑名单
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SysBlacklistBo bo) {
        SysBlacklist update = MapstructUtils.convert(bo, SysBlacklist.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysBlacklist entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除系统黑名单信息
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
