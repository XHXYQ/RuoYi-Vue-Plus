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
import org.dromara.system.domain.bo.AttRuleOvertimeBo;
import org.dromara.system.domain.vo.AttRuleOvertimeVo;
import org.dromara.system.domain.AttRuleOvertime;
import org.dromara.system.mapper.AttRuleOvertimeMapper;
import org.dromara.system.service.IAttRuleOvertimeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 加班规则Service业务层处理
 *
 * @author Lion Li
 * @date 2025-06-13
 */
@RequiredArgsConstructor
@Service
public class AttRuleOvertimeServiceImpl implements IAttRuleOvertimeService {

    private final AttRuleOvertimeMapper baseMapper;

    /**
     * 查询加班规则
     *
     * @param id 主键
     * @return 加班规则
     */
    @Override
    public AttRuleOvertimeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询加班规则列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 加班规则分页列表
     */
    @Override
    public TableDataInfo<AttRuleOvertimeVo> queryPageList(AttRuleOvertimeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AttRuleOvertime> lqw = buildQueryWrapper(bo);
        Page<AttRuleOvertimeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的加班规则列表
     *
     * @param bo 查询条件
     * @return 加班规则列表
     */
    @Override
    public List<AttRuleOvertimeVo> queryList(AttRuleOvertimeBo bo) {
        LambdaQueryWrapper<AttRuleOvertime> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AttRuleOvertime> buildQueryWrapper(AttRuleOvertimeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AttRuleOvertime> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(AttRuleOvertime::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getGroupsId()), AttRuleOvertime::getGroupsId, bo.getGroupsId());
        lqw.like(StringUtils.isNotBlank(bo.getName()), AttRuleOvertime::getName, bo.getName());
        lqw.eq(bo.getDirector() != null, AttRuleOvertime::getDirector, bo.getDirector());
        lqw.eq(StringUtils.isNotBlank(bo.getDetail()), AttRuleOvertime::getDetail, bo.getDetail());
        lqw.eq(bo.getDurationUnit() != null, AttRuleOvertime::getDurationUnit, bo.getDurationUnit());
        lqw.eq(bo.getToIntegerRule() != null, AttRuleOvertime::getToIntegerRule, bo.getToIntegerRule());
        lqw.eq(bo.getToInterValue() != null, AttRuleOvertime::getToInterValue, bo.getToInterValue());
        lqw.eq(StringUtils.isNotBlank(bo.getHourToDay()), AttRuleOvertime::getHourToDay, bo.getHourToDay());
        lqw.eq(bo.getRiskWarningStatus() != null, AttRuleOvertime::getRiskWarningStatus, bo.getRiskWarningStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getRiskWarning()), AttRuleOvertime::getRiskWarning, bo.getRiskWarning());
        lqw.eq(bo.getBigDurationTimeStatus() != null, AttRuleOvertime::getBigDurationTimeStatus, bo.getBigDurationTimeStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getBigDurationTime()), AttRuleOvertime::getBigDurationTime, bo.getBigDurationTime());
        lqw.eq(bo.getStartOrEndStatus() != null, AttRuleOvertime::getStartOrEndStatus, bo.getStartOrEndStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getStartOrEnd()), AttRuleOvertime::getStartOrEnd, bo.getStartOrEnd());
        lqw.eq(bo.getStatus() != null, AttRuleOvertime::getStatus, bo.getStatus());
        lqw.eq(bo.getIsDefault() != null, AttRuleOvertime::getIsDefault, bo.getIsDefault());
        lqw.eq(bo.getDeletedAt() != null, AttRuleOvertime::getDeletedAt, bo.getDeletedAt());
        return lqw;
    }

    /**
     * 新增加班规则
     *
     * @param bo 加班规则
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AttRuleOvertimeBo bo) {
        AttRuleOvertime add = MapstructUtils.convert(bo, AttRuleOvertime.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改加班规则
     *
     * @param bo 加班规则
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AttRuleOvertimeBo bo) {
        AttRuleOvertime update = MapstructUtils.convert(bo, AttRuleOvertime.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AttRuleOvertime entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除加班规则信息
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
