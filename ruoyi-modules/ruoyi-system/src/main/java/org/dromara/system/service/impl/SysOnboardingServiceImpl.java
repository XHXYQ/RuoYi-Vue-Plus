package org.dromara.system.service.impl;

import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.system.domain.bo.SysUserBo;
import org.dromara.system.mapper.SysDeptMapper;
import org.dromara.system.mapper.SysPostMapper;
import org.dromara.system.service.ISysUserService;
import org.springframework.stereotype.Service;
import org.dromara.system.domain.bo.SysOnboardingBo;
import org.dromara.system.domain.vo.SysOnboardingVo;
import org.dromara.system.domain.SysOnboarding;
import org.dromara.system.mapper.SysOnboardingMapper;
import org.dromara.system.service.ISysOnboardingService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 员工入职申请Service业务层处理
 *
 * @author Tim
 * @date 2025-06-03
 */
@RequiredArgsConstructor
@Service
public class SysOnboardingServiceImpl implements ISysOnboardingService {

    private final SysOnboardingMapper baseMapper;
    private final ISysUserService userService; // ✅ 调用已有用户服务即可
    private final SysDeptMapper deptMapper;   // 可用于入职回显或联动
    private final SysPostMapper postMapper;   // 可用于岗位选择

    /**
     * 查询员工入职申请
     *
     * @param onboardingId 主键
     * @return 员工入职申请
     */
    @Override
    public SysOnboardingVo queryById(Long onboardingId){
        return baseMapper.selectVoById(onboardingId);
    }

    /**
     * 分页查询员工入职申请列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 员工入职申请分页列表
     */
    @Override
    public TableDataInfo<SysOnboardingVo> queryPageList(SysOnboardingBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysOnboarding> lqw = buildQueryWrapper(bo);
        Page<SysOnboardingVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的员工入职申请列表
     *
     * @param bo 查询条件
     * @return 员工入职申请列表
     */
    @Override
    public List<SysOnboardingVo> queryList(SysOnboardingBo bo) {
        LambdaQueryWrapper<SysOnboarding> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysOnboarding> buildQueryWrapper(SysOnboardingBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysOnboarding> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(SysOnboarding::getOnboardingId);
        lqw.eq(bo.getUserId() != null, SysOnboarding::getUserId, bo.getUserId());
        lqw.like(StringUtils.isNotBlank(bo.getUserName()), SysOnboarding::getUserName, bo.getUserName());
        lqw.eq(bo.getDeptId() != null, SysOnboarding::getDeptId, bo.getDeptId());
        lqw.eq(bo.getPostId() != null, SysOnboarding::getPostId, bo.getPostId());
        lqw.eq(bo.getExpectedDate() != null, SysOnboarding::getExpectedDate, bo.getExpectedDate());
        lqw.eq(bo.getActualDate() != null, SysOnboarding::getActualDate, bo.getActualDate());
        lqw.eq(bo.getProbationMonths() != null, SysOnboarding::getProbationMonths, bo.getProbationMonths());
        lqw.eq(bo.getSalary() != null, SysOnboarding::getSalary, bo.getSalary());
        lqw.eq(bo.getContractStart() != null, SysOnboarding::getContractStart, bo.getContractStart());
        lqw.eq(bo.getContractEnd() != null, SysOnboarding::getContractEnd, bo.getContractEnd());
        lqw.eq(StringUtils.isNotBlank(bo.getSource()), SysOnboarding::getSource, bo.getSource());
        lqw.eq(bo.getStatus() != null, SysOnboarding::getStatus, bo.getStatus());
        lqw.eq(bo.getCertificationFlag() != null, SysOnboarding::getCertificationFlag, bo.getCertificationFlag());
        lqw.eq(StringUtils.isNotBlank(bo.getRegistrationForm()), SysOnboarding::getRegistrationForm, bo.getRegistrationForm());
        return lqw;
    }

    /**
     * 新增员工入职申请
     *
     * @param bo 员工入职申请
     * @return 是否新增成功
     */
//    @Override
//    public Boolean insertByBo(SysOnboardingBo bo) {
//        SysOnboarding add = MapstructUtils.convert(bo, SysOnboarding.class);
//        validEntityBeforeSave(add);
//        boolean flag = baseMapper.insert(add) > 0;
//        if (flag) {
//            bo.setOnboardingId(add.getOnboardingId());
//        }
//        return flag;
//    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertByBo(SysOnboardingBo bo) {
        // 1. 先创建用户对象
        SysUserBo userBo = new SysUserBo();
        userBo.setUserName(bo.getUserName()); // 假设用户账号为用户名
        userBo.setNickName(bo.getUserName()); // 同步昵称
//        userBo.setPhonenumber(bo.getPhonenumber());
//        userBo.setEmail(bo.getEmail());
        userBo.setDeptId(bo.getDeptId());
        userBo.setPostIds(new Long[]{bo.getPostId()}); // 入职申请中只有一个岗位
        userBo.setStatus("0"); // 默认正常
        userBo.setPassword("123456"); // 初始密码（可加密后保存）

        // 用户插入，自动生成 userId
        int rows = userService.insertUser(userBo);
        if (rows <= 0) throw new ServiceException("新增用户失败");

        // 2. 写入 userId 到入职信息
        bo.setUserId(userBo.getUserId());

        // 3. 转换入职实体保存
        SysOnboarding entity = MapstructUtils.convert(bo, SysOnboarding.class);
        validEntityBeforeSave(entity);
        boolean flag = baseMapper.insert(entity) > 0;
        if (flag) {
            bo.setOnboardingId(entity.getOnboardingId());
        }
        return flag;
    }



    /**
     * 修改员工入职申请
     *
     * @param bo 员工入职申请
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SysOnboardingBo bo) {
        SysOnboarding update = MapstructUtils.convert(bo, SysOnboarding.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysOnboarding entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除员工入职申请信息
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
