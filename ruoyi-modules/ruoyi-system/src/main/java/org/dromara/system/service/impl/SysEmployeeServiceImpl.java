package org.dromara.system.service.impl;

import org.dromara.common.mybatis.core.page.TableDataInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.system.domain.SysDept;
import org.dromara.system.domain.SysEmployee;
import org.dromara.system.domain.SysUser;
import org.dromara.system.domain.bo.SysEmployeeBo;
import org.dromara.system.domain.vo.EmployeeStatsVo;
import org.dromara.system.domain.vo.SysEmployeeVo;
import org.dromara.system.mapper.SysDeptMapper;
import org.dromara.system.mapper.SysEmployeeMapper;
import org.dromara.system.mapper.SysUserMapper;
import org.dromara.system.service.ISysEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysEmployeeServiceImpl implements ISysEmployeeService {

    private final SysEmployeeMapper employeeMapper;
    private final SysDeptMapper deptMapper;
    private final SysUserMapper userMapper;

    @Override
    public int insertEmployee(SysEmployeeBo bo) {
        SysEmployee employee = convertBoToEntity(bo);
        return employeeMapper.insert(employee);
    }

    @Override
    public int updateEmployee(SysEmployeeBo bo) {
        SysEmployee employee = convertBoToEntity(bo);
        return employeeMapper.updateById(employee);
    }

    @Override
    public int deleteEmployeeByIds(Long[] ids) {
        return employeeMapper.deleteBatchIds(List.of(ids));
    }

    @Override
    public SysEmployeeVo selectEmployeeById(Long id) {
        SysEmployee employee = employeeMapper.selectById(id);
        return convertEntityToVo(employee);
    }

    @Override
    public TableDataInfo<SysEmployeeVo> selectPageEmployeeList(SysEmployeeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysEmployee> lqw = buildQueryWrapper(bo);
        Page<SysEmployee> page = employeeMapper.selectPage(pageQuery.build(), lqw);

        List<SysEmployeeVo> vos = page.getRecords().stream()
            .map(this::convertEntityToVo)
            .collect(Collectors.toList());

        return PageUtils.buildTableDataInfo(page, vos);
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
    public List<SysEmployeeVo> selectEmployeeList(SysEmployeeBo bo) {
        LambdaQueryWrapper<SysEmployee> lqw = buildQueryWrapper(bo);
        return employeeMapper.selectList(lqw).stream()
            .map(this::convertEntityToVo)
            .collect(Collectors.toList());
    }

    @Override
    public long getActiveEmployeeCount() {
        LambdaQueryWrapper<SysEmployee> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SysEmployee::getStatusType, "在职");
        return employeeMapper.selectCount(lqw);
    }

    private LambdaQueryWrapper<SysEmployee> buildQueryWrapper(SysEmployeeBo bo) {
        LambdaQueryWrapper<SysEmployee> lqw = Wrappers.lambdaQuery();

        lqw.like(StringUtils.isNotBlank(bo.getName()), SysEmployee::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getPhonenumber()), SysEmployee::getPhonenumber, bo.getPhonenumber());
        lqw.eq(bo.getDeptId() != null, SysEmployee::getDeptId, bo.getDeptId());
        lqw.eq(StringUtils.isNotBlank(bo.getPosition()), SysEmployee::getPosition, bo.getPosition());
        lqw.eq(StringUtils.isNotBlank(bo.getEmployeeType()), SysEmployee::getEmployeeType, bo.getEmployeeType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatusType()), SysEmployee::getStatusType, bo.getStatusType());

        return lqw;
    }

    private SysEmployee convertBoToEntity(SysEmployeeBo bo) {
        SysEmployee entity = new SysEmployee();
        entity.setUserId(bo.getUserId());
        entity.setName(bo.getName());
        entity.setPhonenumber(bo.getPhonenumber());
        entity.setDeptId(bo.getDeptId());
        entity.setPosition(bo.getPosition());
        entity.setEmployeeType(bo.getEmployeeType());
        entity.setStatusType(bo.getStatusType());
        entity.setStartDate(bo.getStartDate());
        return entity;
    }

    private SysEmployeeVo convertEntityToVo(SysEmployee entity) {
        SysEmployeeVo vo = new SysEmployeeVo();
        vo.setUserId(entity.getUserId());
        vo.setName(entity.getName());
        vo.setPhonenumber(entity.getPhonenumber());
        vo.setDeptId(entity.getDeptId());
        vo.setPosition(entity.getPosition());
        vo.setEmployeeType(entity.getEmployeeType());
        vo.setStatusType(entity.getStatusType());
        vo.setStartDate(entity.getStartDate());

        if (entity.getDeptId() != null) {
            SysDept dept = deptMapper.selectById(entity.getDeptId());
            vo.setDeptName(dept != null ? dept.getDeptName() : null);
        }

        return vo;
    }

    /**
     * 新增员工并同步到用户表（事务管理）
     */
    @Override
    @Transactional
    public boolean insertEmployeeAndUser(SysEmployeeBo bo) {
        // 1. 插入员工表
        SysEmployee employee = new SysEmployee();
        BeanUtils.copyProperties(bo, employee);
        int empRows = employeeMapper.insert(employee);

        // 2. 同步到用户表（字段名一致，直接拷贝）
        SysUser user = new SysUser();
        BeanUtils.copyProperties(bo, user);

        int userRows = userMapper.insert(user);

        return empRows > 0 && userRows > 0;
    }

    public EmployeeStatsVo getEmployeeStats() {
        return employeeMapper.selectEmployeeStats();
    }
}
