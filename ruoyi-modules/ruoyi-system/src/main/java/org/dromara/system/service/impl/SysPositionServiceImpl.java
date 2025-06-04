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
import org.dromara.system.domain.bo.SysPositionBo;
import org.dromara.system.domain.vo.SysPositionVo;
import org.dromara.system.domain.SysPosition;
import org.dromara.system.mapper.SysPositionMapper;
import org.dromara.system.service.ISysPositionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 系统职位管理Service业务层处理
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@RequiredArgsConstructor
@Service
public class SysPositionServiceImpl implements ISysPositionService {

    private final SysPositionMapper baseMapper;

    /**
     * 查询系统职位管理
     *
     * @param id 主键
     * @return 系统职位管理
     */
    @Override
    public SysPositionVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询系统职位管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 系统职位管理分页列表
     */
    @Override
    public TableDataInfo<SysPositionVo> queryPageList(SysPositionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysPosition> lqw = buildQueryWrapper(bo);
        Page<SysPositionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的系统职位管理列表
     *
     * @param bo 查询条件
     * @return 系统职位管理列表
     */
    @Override
    public List<SysPositionVo> queryList(SysPositionBo bo) {
        LambdaQueryWrapper<SysPosition> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysPosition> buildQueryWrapper(SysPositionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysPosition> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SysPosition::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), SysPosition::getCode, bo.getCode());
        lqw.like(StringUtils.isNotBlank(bo.getName()), SysPosition::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), SysPosition::getDescription, bo.getDescription());
        lqw.eq(bo.getCreatedAt() != null, SysPosition::getCreatedAt, bo.getCreatedAt());
        lqw.eq(bo.getUpdatedAt() != null, SysPosition::getUpdatedAt, bo.getUpdatedAt());
        return lqw;
    }

    /**
     * 新增系统职位管理
     *
     * @param bo 系统职位管理
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SysPositionBo bo) {
        SysPosition add = MapstructUtils.convert(bo, SysPosition.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改系统职位管理
     *
     * @param bo 系统职位管理
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SysPositionBo bo) {
        SysPosition update = MapstructUtils.convert(bo, SysPosition.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysPosition entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除系统职位管理信息
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
