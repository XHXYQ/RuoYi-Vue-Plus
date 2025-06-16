package org.dromara.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.system.domain.*;
import org.dromara.system.domain.bo.SysContractBo;
import org.dromara.system.domain.vo.SysContractVo;
import org.dromara.system.mapper.*;
import org.dromara.system.service.ISysContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SysContractServiceImpl implements ISysContractService {

    private final SysContractMapper contractMapper;
    private final SysDeptMapper deptMapper;
    private final SysUserMapper userMapper;// 添加到类成员变量

    @Override
    public int insertContract(SysContractBo bo) {
        SysContract contract = convertBoToEntity(bo);
        return contractMapper.insert(contract);
    }

    @Override
    public int updateContract(SysContractBo bo) {
        SysContract contract = convertBoToEntity(bo);
        return contractMapper.updateById(contract);
    }

    @Override
    public int deleteContractByIds(Long[] ids) {
        return contractMapper.deleteBatchIds(List.of(ids));
    }

    @Override
    public SysContractVo selectContractById(Long id) {
        SysContract contract = contractMapper.selectById(id);
        return convertEntityToVo(contract);
    }

    @Override
    public TableDataInfo<SysContractVo> selectPageContractList(SysContractBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysContract> lqw = buildQueryWrapper(bo);
        Page<SysContract> page = contractMapper.selectPage(pageQuery.build(), lqw);

        List<SysContractVo> vos = page.getRecords().stream()
            .map(this::convertEntityToVo)
            .collect(Collectors.toList());

        return SysContractServiceImpl.PageUtils.buildTableDataInfo(page, vos);
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
    public List<SysContractVo> selectContractList(SysContractBo bo) {
        LambdaQueryWrapper<SysContract> lqw = buildQueryWrapper(bo);
        return contractMapper.selectList(lqw).stream()
            .map(this::convertEntityToVo)
            .collect(Collectors.toList());
    }



    private LambdaQueryWrapper<SysContract> buildQueryWrapper(SysContractBo bo) {
        LambdaQueryWrapper<SysContract> lqw = Wrappers.lambdaQuery();

        lqw.like(StringUtils.isNotBlank(bo.getName()), SysContract::getName, bo.getName());
        lqw.eq(bo.getDeptId() != null, SysContract::getDeptId, bo.getDeptId());
        lqw.eq(StringUtils.isNotBlank(bo.getEmployeeType()), SysContract::getEmployeeType, bo.getEmployeeType());

        return lqw;
    }

    private SysContract convertBoToEntity(SysContractBo bo) {
        SysContract entity = new SysContract();
        entity.setUserId(bo.getUserId());
        entity.setName(bo.getName());
        entity.setJobnumber(bo.getJobnumber());
        entity.setDeptId(bo.getDeptId());
        entity.setEmployeeType(bo.getEmployeeType());
        entity.setContractnumber(bo.getContractnumber());
        entity.setContractType(bo.getContractType());
        entity.setStatusType(bo.getStatusType());
        entity.setDeadlineType(bo.getDeadlineType());
        entity.setStartDate(bo.getStartDate());
        entity.setEndDate(bo.getEndDate());
        entity.setStatusxuType(bo.getStatusxuType());
        entity.setCompany(bo.getCompany());
        entity.setSignDate(bo.getSignDate());

        return entity;
    }

    private SysContractVo convertEntityToVo(SysContract entity) {
        SysContractVo vo = new SysContractVo();
        vo.setUserId(entity.getUserId());
        vo.setName(entity.getName());
        vo.setJobnumber(entity.getJobnumber());
        vo.setDeptId(entity.getDeptId());
        vo.setEmployeeType(entity.getEmployeeType());
        vo.setContractnumber(entity.getContractnumber());
        vo.setContractType(entity.getContractType());
        vo.setStatusType(entity.getStatusType());
        vo.setDeadlineType(entity.getDeadlineType());
        vo.setStartDate(entity.getStartDate());
        vo.setEndDate(entity.getEndDate());
        vo.setStatusxuType(entity.getStatusxuType());
        vo.setCompany(entity.getCompany());
        vo.setSignDate(entity.getSignDate());

        if (entity.getDeptId() != null) {
            SysDept dept = deptMapper.selectById(entity.getDeptId());
            vo.setDeptName(dept != null ? dept.getDeptName() : null);
        }

        return vo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertContractAndUser(SysContractBo bo) {
        // 1. 参数校验（可选）
        if (bo == null) {
            throw new IllegalArgumentException("离职信息不能为空");
        }

        // 2. 插入离职记录
        SysContract contract = new SysContract();
        BeanUtils.copyProperties(bo, contract);
        int contractInsertResult = contractMapper.insert(contract);

        // 3. 返回操作结果（仅检查离职记录是否插入成功）
        return contractInsertResult > 0;
    }

}
