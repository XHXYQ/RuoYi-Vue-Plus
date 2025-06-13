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
    private final SysHolidayUserMapper holidayUserMapper;


    @Override
    public List<Long> getHolidayScopeUserIds(Long holidayId) {
        // 参数校验
        if (holidayId == null) {
            throw new IllegalArgumentException("假期ID不能为空");
        }

        // 查询关联用户
        LambdaQueryWrapper<SysHolidayUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysHolidayUser::getHolidayId, holidayId)
            .select(SysHolidayUser::getUserId);

        List<SysHolidayUser> relations = holidayUserMapper.selectList(queryWrapper);

        // 提取用户ID列表
        return relations.stream()
            .map(SysHolidayUser::getUserId)
            .collect(Collectors.toList());
    }

    @Override
    public List<SysHolidayUser> getHolidayScopeUsers(Long holidayId) {
        if (holidayId == null) {
            throw new IllegalArgumentException("假期ID不能为空");
        }

        LambdaQueryWrapper<SysHolidayUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysHolidayUser::getHolidayId, holidayId)
            .select(SysHolidayUser::getUserId, SysHolidayUser::getNickName); // 确保选择这两个字段

        return holidayUserMapper.selectList(queryWrapper);
    }

    @Override
    public int insertHoliday(SysHolidayBo bo) {
        SysHoliday holiday = convertBoToEntity(bo);
        return holidayMapper.insert(holiday);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateHoliday(SysHolidayBo bo) {
        // 1. 更新假期规则
        SysHoliday holiday = convertBoToEntity(bo);
        int result = holidayMapper.updateById(holiday);

        // 2. 更新关联用户（先删除旧的，再添加新的）
        if (bo.getSelectedUserIds() != null && bo.getSelectedUserNickNames() != null
            && bo.getSelectedUserIds().size() == bo.getSelectedUserNickNames().size()) {

            // 删除旧关联
            LambdaQueryWrapper<SysHolidayUser> deleteWrapper = new LambdaQueryWrapper<>();
            deleteWrapper.eq(SysHolidayUser::getHolidayId, bo.getHolidayId());
            holidayUserMapper.delete(deleteWrapper);

            // 添加新关联
            List<SysHolidayUser> relations = new ArrayList<>();
            for (int i = 0; i < bo.getSelectedUserIds().size(); i++) {
                SysHolidayUser relation = new SysHolidayUser();
                relation.setHolidayId(bo.getHolidayId());
                relation.setUserId(bo.getSelectedUserIds().get(i));
                relation.setNickName(bo.getSelectedUserNickNames().get(i));
                relations.add(relation);
            }

            relations.forEach(holidayUserMapper::insert);
        }

        return result;
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
        // 1. 保存假期规则
        SysHoliday holiday = convertBoToEntity(bo);
        int result = holidayMapper.insert(holiday);
        if (result <= 0) {
            return false;
        }

        // 2. 处理关联用户（仅当scopeType为"部门/人员"时）
        if ("部门/人员".equals(bo.getScopeType()) &&
            CollectionUtils.isNotEmpty(bo.getSelectedUserIds())) {

            // 获取假期规则详情用于填充关联表
            SysHolidayVo holidayDetail = selectHolidayById(holiday.getHolidayId());

            List<SysHolidayUser> relations = new ArrayList<>();
            for (int i = 0; i < bo.getSelectedUserIds().size(); i++) {
                SysHolidayUser relation = new SysHolidayUser();
                relation.setHolidayId(holiday.getHolidayId());
                relation.setUserId(bo.getSelectedUserIds().get(i));
                relation.setNickName(bo.getSelectedUserNickNames().get(i));
                // 新增关键字段
                relation.setScopeType(bo.getScopeType());
                relation.setBalanceType(holidayDetail.getBalanceType());
                relation.setHolidayName(holidayDetail.getName());
                relations.add(relation);
            }

            // 批量插入
            relations.forEach(holidayUserMapper::insert);
        }

        return true;
    }

    @Override
    public List<EmployeeHolidayBalanceVo> getEmployeeHolidayBalances(Long employeeId) {
        // 1. 查询所有假期规则
        List<SysHoliday> allHolidays = holidayMapper.selectList(new LambdaQueryWrapper<>());

        // 2. 查询该员工关联的所有假期规则
        LambdaQueryWrapper<SysHolidayUser> userHolidayQuery = new LambdaQueryWrapper<>();
        userHolidayQuery.eq(SysHolidayUser::getUserId, employeeId);
        List<SysHolidayUser> userHolidays = holidayUserMapper.selectList(userHolidayQuery);

        // 3. 组装返回结果
        return allHolidays.stream()
            .filter(holiday -> {
                // 如果假期规则是"全公司"范围，则所有员工都有
                if ("全公司".equals(holiday.getScopeType())) {
                    return true;
                }
                // 否则检查是否在特定员工列表中
                return userHolidays.stream()
                    .anyMatch(uh -> uh.getHolidayId().equals(holiday.getHolidayId()));
            })
            .map(holiday -> {
                EmployeeHolidayBalanceVo vo = new EmployeeHolidayBalanceVo();
                vo.setHolidayId(holiday.getHolidayId());
                vo.setHolidayName(holiday.getName());
                vo.setBalanceType(holiday.getBalanceType());
                return vo;
            })
            .collect(Collectors.toList());
    }

}
