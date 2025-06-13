package org.dromara.system.service.impl;

import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.json.utils.JsonUtils;
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
 * @author Skye
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

        // 处理 groupsId（List<Long> -> JSON String）
        if (bo.getGroupsId() != null && !bo.getGroupsId().isEmpty()) {
            lqw.eq(AttRuleOvertime::getGroupsId, JsonUtils.toJsonString(bo.getGroupsId()));
        }

        // 处理其他JSON字段
        if (bo.getDetail() != null && !bo.getDetail().isEmpty()) {
            lqw.eq(AttRuleOvertime::getDetail, JsonUtils.toJsonString(bo.getDetail()));
        }

        if (bo.getRiskWarning() != null && !bo.getRiskWarning().isEmpty()) {
            lqw.eq(AttRuleOvertime::getRiskWarning, JsonUtils.toJsonString(bo.getRiskWarning()));
        }

        if (bo.getBigDurationTime() != null && !bo.getBigDurationTime().isEmpty()) {
            lqw.eq(AttRuleOvertime::getBigDurationTime, JsonUtils.toJsonString(bo.getBigDurationTime()));
        }

        if (bo.getStartOrEnd() != null && !bo.getStartOrEnd().isEmpty()) {
            lqw.eq(AttRuleOvertime::getStartOrEnd, JsonUtils.toJsonString(bo.getStartOrEnd()));
        }

        // 处理普通字段（保持不变）
        lqw.like(StringUtils.isNotBlank(bo.getName()), AttRuleOvertime::getName, bo.getName());
        lqw.eq(bo.getDirector() != null, AttRuleOvertime::getDirector, bo.getDirector());
        lqw.orderByAsc(AttRuleOvertime::getId);
        lqw.like(StringUtils.isNotBlank(bo.getName()), AttRuleOvertime::getName, bo.getName());
        lqw.eq(bo.getDirector() != null, AttRuleOvertime::getDirector, bo.getDirector());
        lqw.eq(bo.getDurationUnit() != null, AttRuleOvertime::getDurationUnit, bo.getDurationUnit());
        lqw.eq(bo.getToIntegerRule() != null, AttRuleOvertime::getToIntegerRule, bo.getToIntegerRule());
        lqw.eq(bo.getToInterValue() != null, AttRuleOvertime::getToInterValue, bo.getToInterValue());
        lqw.eq(StringUtils.isNotBlank(bo.getHourToDay()), AttRuleOvertime::getHourToDay, bo.getHourToDay());
        lqw.eq(bo.getRiskWarningStatus() != null, AttRuleOvertime::getRiskWarningStatus, bo.getRiskWarningStatus());
        lqw.eq(bo.getBigDurationTimeStatus() != null, AttRuleOvertime::getBigDurationTimeStatus, bo.getBigDurationTimeStatus());
        lqw.eq(bo.getStartOrEndStatus() != null, AttRuleOvertime::getStartOrEndStatus, bo.getStartOrEndStatus());
        lqw.eq(bo.getStatus() != null, AttRuleOvertime::getStatus, bo.getStatus());
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
        AttRuleOvertime entity = new AttRuleOvertime();

        // 直接赋值，MyBatis-Plus 会自动处理 JSON 转换
        entity.setGroupsId(JsonUtils.toJsonString(bo.getGroupsId()));
        entity.setDetail(JsonUtils.toJsonString(bo.getDetail()));
        entity.setRiskWarning(JsonUtils.toJsonString(bo.getRiskWarning()));
        entity.setBigDurationTime(JsonUtils.toJsonString(bo.getBigDurationTime()));
        entity.setStartOrEnd(JsonUtils.toJsonString(bo.getStartOrEnd()));

        // 处理普通字段
        entity.setName(bo.getName());
        entity.setDirector(bo.getDirector());
        entity.setDurationUnit(bo.getDurationUnit());
        entity.setToIntegerRule(bo.getToIntegerRule());
        entity.setToInterValue(bo.getToInterValue());
        entity.setHourToDay(bo.getHourToDay());
        entity.setRiskWarningStatus(bo.getRiskWarningStatus());
        entity.setBigDurationTimeStatus(bo.getBigDurationTimeStatus());
        entity.setStartOrEndStatus(bo.getStartOrEndStatus());
        entity.setStatus(bo.getStatus() != null ? bo.getStatus() : 1L); // 默认启用

        validEntityBeforeSave(entity);
        boolean flag = baseMapper.insert(entity) > 0;
        if (flag) {
            bo.setId(entity.getId());
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
        AttRuleOvertime entity = baseMapper.selectById(bo.getId());
        if (entity == null) {
            throw new ServiceException("加班规则不存在");
        }

        // 2. 更新JSON字段（需要手动转换为JSON字符串）
        if (bo.getGroupsId() != null) {
            entity.setGroupsId(JsonUtils.toJsonString(bo.getGroupsId()));
        }
        if (bo.getDetail() != null) {
            entity.setDetail(JsonUtils.toJsonString(bo.getDetail()));
        }
        if (bo.getRiskWarning() != null) {
            entity.setRiskWarning(JsonUtils.toJsonString(bo.getRiskWarning()));
        }
        if (bo.getBigDurationTime() != null) {
            entity.setBigDurationTime(JsonUtils.toJsonString(bo.getBigDurationTime()));
        }
        if (bo.getStartOrEnd() != null) {
            entity.setStartOrEnd(JsonUtils.toJsonString(bo.getStartOrEnd()));
        }

        // 3. 更新普通字段
        if (StringUtils.isNotBlank(bo.getName())) {
            entity.setName(bo.getName());
        }
        if (bo.getDirector() != null) {
            entity.setDirector(bo.getDirector());
        }
        if (bo.getDurationUnit() != null) {
            entity.setDurationUnit(bo.getDurationUnit());
        }
        if (bo.getToIntegerRule() != null) {
            entity.setToIntegerRule(bo.getToIntegerRule());
        }
        if (bo.getToInterValue() != null) {
            entity.setToInterValue(bo.getToInterValue());
        }
        if (StringUtils.isNotBlank(bo.getHourToDay())) {
            entity.setHourToDay(bo.getHourToDay());
        }
        if (bo.getRiskWarningStatus() != null) {
            entity.setRiskWarningStatus(bo.getRiskWarningStatus());
        }
        if (bo.getBigDurationTimeStatus() != null) {
            entity.setBigDurationTimeStatus(bo.getBigDurationTimeStatus());
        }
        if (bo.getStartOrEndStatus() != null) {
            entity.setStartOrEndStatus(bo.getStartOrEndStatus());
        }
        if (bo.getStatus() != null) {
            entity.setStatus(bo.getStatus());
        }
        validEntityBeforeSave(entity);
        return baseMapper.updateById(entity) > 0;
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
