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
import org.dromara.system.domain.bo.SysCompanyManagementBo;
import org.dromara.system.domain.vo.SysCompanyManagementVo;
import org.dromara.system.domain.SysCompanyManagement;
import org.dromara.system.mapper.SysCompanyManagementMapper;
import org.dromara.system.service.ISysCompanyManagementService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 公司组织管理Service业务层处理
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@RequiredArgsConstructor
@Service
public class SysCompanyManagementServiceImpl implements ISysCompanyManagementService {

    private final SysCompanyManagementMapper baseMapper;

    /**
     * 查询公司组织管理
     *
     * @param id 主键
     * @return 公司组织管理
     */
    @Override
    public SysCompanyManagementVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询公司组织管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 公司组织管理分页列表
     */
    @Override
    public TableDataInfo<SysCompanyManagementVo> queryPageList(SysCompanyManagementBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysCompanyManagement> lqw = buildQueryWrapper(bo);
        Page<SysCompanyManagementVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的公司组织管理列表
     *
     * @param bo 查询条件
     * @return 公司组织管理列表
     */
    @Override
    public List<SysCompanyManagementVo> queryList(SysCompanyManagementBo bo) {
        LambdaQueryWrapper<SysCompanyManagement> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysCompanyManagement> buildQueryWrapper(SysCompanyManagementBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysCompanyManagement> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SysCompanyManagement::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), SysCompanyManagement::getCode, bo.getCode());
        lqw.like(StringUtils.isNotBlank(bo.getName()), SysCompanyManagement::getName, bo.getName());
        lqw.eq(bo.getEmployeeCount() != null, SysCompanyManagement::getEmployeeCount, bo.getEmployeeCount());
        lqw.eq(StringUtils.isNotBlank(bo.getType()), SysCompanyManagement::getType, bo.getType());
        lqw.eq(StringUtils.isNotBlank(bo.getManager()), SysCompanyManagement::getManager, bo.getManager());
        return lqw;
    }

    /**
     * 新增公司组织管理
     *
     * @param bo 公司组织管理
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SysCompanyManagementBo bo) {
        SysCompanyManagement add = MapstructUtils.convert(bo, SysCompanyManagement.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改公司组织管理
     *
     * @param bo 公司组织管理
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SysCompanyManagementBo bo) {
        SysCompanyManagement update = MapstructUtils.convert(bo, SysCompanyManagement.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysCompanyManagement entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除公司组织管理信息
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
