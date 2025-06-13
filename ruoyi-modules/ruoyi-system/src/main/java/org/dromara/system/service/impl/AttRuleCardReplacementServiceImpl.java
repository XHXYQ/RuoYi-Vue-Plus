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
import org.dromara.system.domain.bo.AttRuleCardReplacementBo;
import org.dromara.system.domain.vo.AttRuleCardReplacementVo;
import org.dromara.system.domain.AttRuleCardReplacement;
import org.dromara.system.mapper.AttRuleCardReplacementMapper;
import org.dromara.system.service.IAttRuleCardReplacementService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 补卡规则Service业务层处理
 *
 * @author Skye
 * @date 2025-06-13
 */
@RequiredArgsConstructor
@Service
public class AttRuleCardReplacementServiceImpl implements IAttRuleCardReplacementService {

    private final AttRuleCardReplacementMapper baseMapper;

    /**
     * 查询补卡规则
     *
     * @param id 主键
     * @return 补卡规则
     */
    @Override
    public AttRuleCardReplacementVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询补卡规则列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 补卡规则分页列表
     */
    @Override
    public TableDataInfo<AttRuleCardReplacementVo> queryPageList(AttRuleCardReplacementBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AttRuleCardReplacement> lqw = buildQueryWrapper(bo);
        Page<AttRuleCardReplacementVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的补卡规则列表
     *
     * @param bo 查询条件
     * @return 补卡规则列表
     */
    @Override
    public List<AttRuleCardReplacementVo> queryList(AttRuleCardReplacementBo bo) {
        LambdaQueryWrapper<AttRuleCardReplacement> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AttRuleCardReplacement> buildQueryWrapper(AttRuleCardReplacementBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AttRuleCardReplacement> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(AttRuleCardReplacement::getId);
        lqw.like(StringUtils.isNotBlank(bo.getName()), AttRuleCardReplacement::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getGroupsId()), AttRuleCardReplacement::getGroupsId, bo.getGroupsId());
        lqw.eq(StringUtils.isNotBlank(bo.getDirector()), AttRuleCardReplacement::getDirector, bo.getDirector());
        lqw.eq(bo.getAllow() != null, AttRuleCardReplacement::getAllow, bo.getAllow());
        lqw.eq(bo.getNum() != null, AttRuleCardReplacement::getNum, bo.getNum());
        lqw.eq(StringUtils.isNotBlank(bo.getDate()), AttRuleCardReplacement::getDate, bo.getDate());
        lqw.eq(bo.getPastDay() != null, AttRuleCardReplacement::getPastDay, bo.getPastDay());
        lqw.eq(bo.getDayType() != null, AttRuleCardReplacement::getDayType, bo.getDayType());
        lqw.eq(StringUtils.isNotBlank(bo.getType()), AttRuleCardReplacement::getType, bo.getType());
        lqw.eq(bo.getDeletedAt() != null, AttRuleCardReplacement::getDeletedAt, bo.getDeletedAt());
        lqw.eq(bo.getIsDefault() != null, AttRuleCardReplacement::getIsDefault, bo.getIsDefault());
        return lqw;
    }

    /**
     * 新增补卡规则
     *
     * @param bo 补卡规则
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AttRuleCardReplacementBo bo) {
        AttRuleCardReplacement add = MapstructUtils.convert(bo, AttRuleCardReplacement.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改补卡规则
     *
     * @param bo 补卡规则
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AttRuleCardReplacementBo bo) {
        AttRuleCardReplacement update = MapstructUtils.convert(bo, AttRuleCardReplacement.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AttRuleCardReplacement entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除补卡规则信息
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
