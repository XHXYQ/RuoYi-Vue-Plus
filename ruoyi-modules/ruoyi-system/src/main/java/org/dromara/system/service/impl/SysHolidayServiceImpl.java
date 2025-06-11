package org.dromara.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.*;
import org.dromara.common.mybatis.core.page.*;
import org.dromara.system.domain.*;
import org.dromara.system.domain.bo.*;
import org.dromara.system.domain.vo.*;
import org.dromara.system.mapper.*;
import org.dromara.system.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SysHolidayServiceImpl implements ISysHolidayService {

    private final SysHolidayMapper holidayMapper;

    @Override
    public int insertHoliday(SysHolidayBo bo) {
        SysHoliday holiday = convertBoToEntity(bo);
        return holidayMapper.insert(holiday);
    }

    @Override
    public int updateHoliday(SysHolidayBo bo) {
        SysHoliday holiday = convertBoToEntity(bo);
        return holidayMapper.updateById(holiday);
    }

    @Override
    public int deleteHolidayByIds(Long[] ids) {
        return holidayMapper.deleteBatchIds(List.of(ids));
    }

    @Override
    public SysHolidayVo selectHolidayById(Long id) {
        SysHoliday holiday = holidayMapper.selectById(id);
        return convertEntityToVo(holiday);
    }

    @Override
    public TableDataInfo<SysHolidayVo> selectPageHolidayList(SysHolidayBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysHoliday> lqw = buildQueryWrapper(bo);
        Page<SysHoliday> page = holidayMapper.selectPage(pageQuery.build(), lqw);

        List<SysHolidayVo> vos = page.getRecords().stream()
            .map(this::convertEntityToVo)
            .collect(Collectors.toList());

        return SysHolidayServiceImpl.PageUtils.buildTableDataInfo(page, vos);
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
    public List<SysHolidayVo> selectHolidayList(SysHolidayBo bo) {
        LambdaQueryWrapper<SysHoliday> lqw = buildQueryWrapper(bo);
        return holidayMapper.selectList(lqw).stream()
            .map(this::convertEntityToVo)
            .collect(Collectors.toList());
    }



    private LambdaQueryWrapper<SysHoliday> buildQueryWrapper(SysHolidayBo bo) {
        LambdaQueryWrapper<SysHoliday> lqw = Wrappers.lambdaQuery();

        lqw.like(StringUtils.isNotBlank(bo.getName()), SysHoliday::getName, bo.getName());

        return lqw;
    }

    private SysHoliday convertBoToEntity(SysHolidayBo bo) {
        SysHoliday entity = new SysHoliday();
        entity.setHolidayId(bo.getHolidayId());
        entity.setName(bo.getName());
        entity.setBalanceType(bo.getBalanceType());
        entity.setMonthlyIssueDate(bo.getMonthlyIssueDate());
        entity.setMonthlyQuotaRule(bo.getMonthlyQuotaRule());
        entity.setMonthlyQuota(bo.getMonthlyQuota());
        entity.setMonthlyValidity(bo.getMonthlyValidity());
        entity.setYearlyIssueDate(bo.getYearlyIssueDate());
        entity.setYearlyQuotaRule(bo.getYearlyQuotaRule());
        entity.setYearlyQuota(bo.getYearlyQuota());
        entity.setYearlyValidity(bo.getYearlyValidity());
        entity.setAllowExtendYearly(bo.getAllowExtendYearly());
        entity.setRetainValue(bo.getRetainValue());
        entity.setRetainUnit(bo.getRetainUnit());
        entity.setManualValidity(bo.getManualValidity());
        entity.setAllowActualWorkdays(bo.getAllowActualWorkdays());
        entity.setNewType(bo.getNewType());
        entity.setMoneyType(bo.getMoneyType());
        entity.setHolidayType(bo.getHolidayType());
        entity.setTimeType(bo.getTimeType());
        entity.setScopeType(bo.getScopeType());
        entity.setExpireReminder(bo.getExpireReminder());
        entity.setRemindTime(bo.getRemindTime());
        entity.setNotifyEmployee(bo.getNotifyEmployee());
        entity.setNotifyManager(bo.getNotifyManager());
        entity.setRoundingUnit(bo.getRoundingUnit());
        entity.setHolidaytimeType(bo.getHolidaytimeType());
        entity.setSocialAgeRules(bo.getSocialAgeRules());
        entity.setCompanyAgeRules(bo.getCompanyAgeRules());

        return entity;
    }

    private SysHolidayVo convertEntityToVo(SysHoliday entity) {
        SysHolidayVo vo = new SysHolidayVo();
        vo.setHolidayId(entity.getHolidayId());
        vo.setName(entity.getName());
        vo.setBalanceType(entity.getBalanceType());
        vo.setMonthlyIssueDate(entity.getMonthlyIssueDate());
        vo.setMonthlyQuotaRule(entity.getMonthlyQuotaRule());
        vo.setMonthlyQuota(entity.getMonthlyQuota());
        vo.setMonthlyValidity(entity.getMonthlyValidity());
        vo.setYearlyIssueDate(entity.getYearlyIssueDate());
        vo.setYearlyQuotaRule(entity.getYearlyQuotaRule());
        vo.setYearlyQuota(entity.getYearlyQuota());
        vo.setYearlyValidity(entity.getYearlyValidity());
        vo.setAllowExtendYearly(entity.getAllowExtendYearly());
        vo.setRetainValue(entity.getRetainValue());
        vo.setRetainUnit(entity.getRetainUnit());
        vo.setManualValidity(entity.getManualValidity());
        vo.setAllowActualWorkdays(entity.getAllowActualWorkdays());
        vo.setNewType(entity.getNewType());
        vo.setMoneyType(entity.getMoneyType());
        vo.setHolidayType(entity.getHolidayType());
        vo.setTimeType(entity.getTimeType());
        vo.setScopeType(entity.getScopeType());
        vo.setExpireReminder(entity.getExpireReminder());
        vo.setRemindTime(entity.getRemindTime());
        vo.setNotifyEmployee(entity.getNotifyEmployee());
        vo.setNotifyManager(entity.getNotifyManager());
        vo.setRoundingUnit(entity.getRoundingUnit());
        vo.setHolidaytimeType(entity.getHolidaytimeType());
        vo.setSocialAgeRules(entity.getSocialAgeRules());
        vo.setCompanyAgeRules(entity.getCompanyAgeRules());

        return vo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertHolidayAndUser(SysHolidayBo bo) {
        // 1. 参数校验（可选）
        if (bo == null) {
            throw new IllegalArgumentException("离职信息不能为空");
        }

        // 2. 插入离职记录
        SysHoliday holiday = new SysHoliday();
        BeanUtils.copyProperties(bo, holiday);
        int holidayInsertResult = holidayMapper.insert(holiday);

        // 3. 返回操作结果（仅检查离职记录是否插入成功）
        return holidayInsertResult > 0;
    }

}
