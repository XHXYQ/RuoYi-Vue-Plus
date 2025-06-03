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
import org.dromara.system.domain.bo.SysOrgTypeBo;
import org.dromara.system.domain.vo.SysOrgTypeVo;
import org.dromara.system.domain.SysOrgType;
import org.dromara.system.mapper.SysOrgTypeMapper;
import org.dromara.system.service.ISysOrgTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 组织类型Service业务层处理
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@RequiredArgsConstructor
@Service
public class SysOrgTypeServiceImpl implements ISysOrgTypeService {

    private final SysOrgTypeMapper baseMapper;

    /**
     * 查询组织类型
     *
     * @param id 主键
     * @return 组织类型
     */
    @Override
    public SysOrgTypeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询组织类型列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 组织类型分页列表
     */
    @Override
    public TableDataInfo<SysOrgTypeVo> queryPageList(SysOrgTypeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysOrgType> lqw = buildQueryWrapper(bo);
        Page<SysOrgTypeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的组织类型列表
     *
     * @param bo 查询条件
     * @return 组织类型列表
     */
    @Override
    public List<SysOrgTypeVo> queryList(SysOrgTypeBo bo) {
        LambdaQueryWrapper<SysOrgType> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysOrgType> buildQueryWrapper(SysOrgTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysOrgType> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SysOrgType::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), SysOrgType::getCode, bo.getCode());
        lqw.like(StringUtils.isNotBlank(bo.getName()), SysOrgType::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), SysOrgType::getDescription, bo.getDescription());
        return lqw;
    }

    /**
     * 新增组织类型
     *
     * @param bo 组织类型
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SysOrgTypeBo bo) {
        SysOrgType add = MapstructUtils.convert(bo, SysOrgType.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改组织类型
     *
     * @param bo 组织类型
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SysOrgTypeBo bo) {
        SysOrgType update = MapstructUtils.convert(bo, SysOrgType.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysOrgType entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除组织类型信息
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
