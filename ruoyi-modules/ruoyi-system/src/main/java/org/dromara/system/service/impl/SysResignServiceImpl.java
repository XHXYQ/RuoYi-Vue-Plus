package org.dromara.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.system.domain.SysDept;
import org.dromara.system.domain.SysEmployee;
import org.dromara.system.domain.SysResign;
import org.dromara.system.domain.SysUser;
import org.dromara.system.domain.bo.SysEmployeeBo;
import org.dromara.system.domain.bo.SysResignBo;
import org.dromara.system.domain.vo.EmployeeStatsVo;
import org.dromara.system.domain.vo.SysEmployeeVo;
import org.dromara.system.domain.vo.SysResignVo;
import org.dromara.system.mapper.SysDeptMapper;
import org.dromara.system.mapper.SysEmployeeMapper;
import org.dromara.system.mapper.SysResignMapper;
import org.dromara.system.mapper.SysUserMapper;
import org.dromara.system.service.ISysResignService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysResignServiceImpl implements ISysResignService {

    private final SysResignMapper resignMapper;
    private final SysDeptMapper deptMapper;
    private final SysUserMapper userMapper; // 添加到类成员变量

    @Override
    public int insertResign(SysResignBo bo) {
        SysResign resign = convertBoToEntity(bo);
        return resignMapper.insert(resign);
    }

    @Override
    public int updateResign(SysResignBo bo) {
        SysResign resign = convertBoToEntity(bo);
        return resignMapper.updateById(resign);
    }

    @Override
    public int deleteResignByIds(Long[] ids) {
        return resignMapper.deleteBatchIds(List.of(ids));
    }

    @Override
    public SysResignVo selectResignById(Long id) {
        SysResign resign = resignMapper.selectById(id);
        return convertEntityToVo(resign);
    }

    @Override
    public TableDataInfo<SysResignVo> selectPageResignList(SysResignBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysResign> lqw = buildQueryWrapper(bo);
        Page<SysResign> page = resignMapper.selectPage(pageQuery.build(), lqw);

        List<SysResignVo> vos = page.getRecords().stream()
            .map(this::convertEntityToVo)
            .collect(Collectors.toList());

        return SysResignServiceImpl.PageUtils.buildTableDataInfo(page, vos);
    }

    public class PageUtils {
        public static <T> TableDataInfo<T> buildTableDataInfo(IPage<?> page, List<T> records) {
            TableDataInfo<T> rspData = new TableDataInfo<>();
            rspData.setRows(records);
            rspData.setTotal(page.getTotal());
            return rspData;
        }
    }

    @Override
    public List<SysResignVo> selectResignList(SysResignBo bo) {
        LambdaQueryWrapper<SysResign> lqw = buildQueryWrapper(bo);
        return resignMapper.selectList(lqw).stream()
            .map(this::convertEntityToVo)
            .collect(Collectors.toList());
    }



    private LambdaQueryWrapper<SysResign> buildQueryWrapper(SysResignBo bo) {
        LambdaQueryWrapper<SysResign> lqw = Wrappers.lambdaQuery();

        lqw.like(StringUtils.isNotBlank(bo.getName()), SysResign::getName, bo.getName());
        lqw.eq(bo.getDeptId() != null, SysResign::getDeptId, bo.getDeptId());
        lqw.eq(StringUtils.isNotBlank(bo.getPosition()), SysResign::getPosition, bo.getPosition());
        lqw.eq(StringUtils.isNotBlank(bo.getEmployeeType()), SysResign::getEmployeeType, bo.getEmployeeType());

        return lqw;
    }

    private SysResign convertBoToEntity(SysResignBo bo) {
        SysResign entity = new SysResign();
        entity.setUserId(bo.getUserId());
        entity.setName(bo.getName());
        entity.setDeptId(bo.getDeptId());
        entity.setPosition(bo.getPosition());
        entity.setEmployeeType(bo.getEmployeeType());
        entity.setStartDate(bo.getStartDate());
        entity.setEndDate(bo.getEndDate());
        entity.setRemark(bo.getRemark());
        return entity;
    }

    private SysResignVo convertEntityToVo(SysResign entity) {
        SysResignVo vo = new SysResignVo();
        vo.setUserId(entity.getUserId());
        vo.setName(entity.getName());
        vo.setDeptId(entity.getDeptId());
        vo.setPosition(entity.getPosition());
        vo.setEmployeeType(entity.getEmployeeType());
        vo.setStartDate(entity.getStartDate());
        vo.setEndDate(entity.getEndDate());
        vo.setRemark(entity.getRemark());

        if (entity.getDeptId() != null) {
            SysDept dept = deptMapper.selectById(entity.getDeptId());
            vo.setDeptName(dept != null ? dept.getDeptName() : null);
        }

        return vo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertResignAndUser(SysResignBo bo) {
        // 1. 参数校验（可选）
        if (bo == null) {
            throw new IllegalArgumentException("离职信息不能为空");
        }

        // 2. 插入离职记录
        SysResign resign = new SysResign();
        BeanUtils.copyProperties(bo, resign);
        int resignInsertResult = resignMapper.insert(resign);

        // 3. 返回操作结果（仅检查离职记录是否插入成功）
        return resignInsertResult > 0;
    }
}
