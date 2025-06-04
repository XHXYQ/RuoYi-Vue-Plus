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
import org.dromara.system.domain.bo.SysSolicitudeRuleBo;
import org.dromara.system.domain.vo.SysSolicitudeRuleVo;
import org.dromara.system.domain.SysSolicitudeRule;
import org.dromara.system.mapper.SysSolicitudeRuleMapper;
import org.dromara.system.service.ISysSolicitudeRuleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 员工关怀规则配置Service业务层处理
 *
 * @author Tim
 * @date 2025-06-04
 */
@RequiredArgsConstructor
@Service
public class SysSolicitudeRuleServiceImpl implements ISysSolicitudeRuleService {

    private final SysSolicitudeRuleMapper baseMapper;

    /**
     * 查询员工关怀规则配置
     *
     * @param id 主键
     * @return 员工关怀规则配置
     */
    @Override
    public SysSolicitudeRuleVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询员工关怀规则配置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 员工关怀规则配置分页列表
     */
    @Override
    public TableDataInfo<SysSolicitudeRuleVo> queryPageList(SysSolicitudeRuleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysSolicitudeRule> lqw = buildQueryWrapper(bo);
        Page<SysSolicitudeRuleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的员工关怀规则配置列表
     *
     * @param bo 查询条件
     * @return 员工关怀规则配置列表
     */
    @Override
    public List<SysSolicitudeRuleVo> queryList(SysSolicitudeRuleBo bo) {
        LambdaQueryWrapper<SysSolicitudeRule> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysSolicitudeRule> buildQueryWrapper(SysSolicitudeRuleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysSolicitudeRule> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SysSolicitudeRule::getId);
        lqw.eq(bo.getCategoryId() != null, SysSolicitudeRule::getCategoryId, bo.getCategoryId());
        lqw.eq(bo.getEnableSystemRemind() != null, SysSolicitudeRule::getEnableSystemRemind, bo.getEnableSystemRemind());
        lqw.eq(bo.getEnableAllRemind() != null, SysSolicitudeRule::getEnableAllRemind, bo.getEnableAllRemind());
        lqw.eq(bo.getEnableColleagueRemind() != null, SysSolicitudeRule::getEnableColleagueRemind, bo.getEnableColleagueRemind());
        lqw.eq(bo.getRemindDaysBefore() != null, SysSolicitudeRule::getRemindDaysBefore, bo.getRemindDaysBefore());
        lqw.eq(bo.getRemindTime() != null, SysSolicitudeRule::getRemindTime, bo.getRemindTime());
        lqw.eq(StringUtils.isNotBlank(bo.getMessageTemplate()), SysSolicitudeRule::getMessageTemplate, bo.getMessageTemplate());
        lqw.eq(bo.getCreatedBy() != null, SysSolicitudeRule::getCreatedBy, bo.getCreatedBy());
        lqw.eq(bo.getCreatedAt() != null, SysSolicitudeRule::getCreatedAt, bo.getCreatedAt());
        lqw.eq(bo.getUpdatedAt() != null, SysSolicitudeRule::getUpdatedAt, bo.getUpdatedAt());
        lqw.eq(bo.getDeletedAt() != null, SysSolicitudeRule::getDeletedAt, bo.getDeletedAt());
        return lqw;
    }

    /**
     * 新增员工关怀规则配置
     *
     * @param bo 员工关怀规则配置
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SysSolicitudeRuleBo bo) {
        SysSolicitudeRule add = MapstructUtils.convert(bo, SysSolicitudeRule.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改员工关怀规则配置
     *
     * @param bo 员工关怀规则配置
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SysSolicitudeRuleBo bo) {
        SysSolicitudeRule update = MapstructUtils.convert(bo, SysSolicitudeRule.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysSolicitudeRule entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除员工关怀规则配置信息
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
