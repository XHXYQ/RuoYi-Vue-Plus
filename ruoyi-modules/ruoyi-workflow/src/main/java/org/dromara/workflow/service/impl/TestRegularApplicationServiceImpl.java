package org.dromara.workflow.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import org.dromara.common.core.domain.event.ProcessCreateTaskEvent;
import org.dromara.common.core.domain.event.ProcessDeleteEvent;
import org.dromara.common.core.domain.event.ProcessEvent;
import org.dromara.common.core.enums.BusinessStatusEnum;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.core.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import lombok.RequiredArgsConstructor;
import org.dromara.workflow.domain.TestRegularApplication;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.dromara.workflow.domain.bo.TestRegularApplicationBo;
import org.dromara.workflow.domain.vo.TestRegularApplicationVo;
//import org.dromara.workflow.domain.TestRegularApplication;
import org.dromara.workflow.mapper.TestRegularApplicationMapper;
import org.dromara.workflow.service.ITestRegularApplicationService;
import org.dromara.workflow.common.ConditionalOnEnable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 转正申请Service业务层处理
 *
 * @author Tim
 * @date 2025-05-24
 */
@ConditionalOnEnable
@RequiredArgsConstructor
@Service
@Slf4j
public class TestRegularApplicationServiceImpl implements ITestRegularApplicationService {

    private final TestRegularApplicationMapper baseMapper;
    private final WorkflowService workflowService;

    /**
     * 查询转正申请
     *
     * @param id 主键
     * @return 转正申请
     */
    @Override
    public TestRegularApplicationVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询转正申请列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 转正申请分页列表
     */
    @Override
    public TableDataInfo<TestRegularApplicationVo> queryPageList(TestRegularApplicationBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TestRegularApplication> lqw = buildQueryWrapper(bo);
        Page<TestRegularApplicationVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的转正申请列表
     *
     * @param bo 查询条件
     * @return 转正申请列表
     */
    @Override
    public List<TestRegularApplicationVo> queryList(TestRegularApplicationBo bo) {
        LambdaQueryWrapper<TestRegularApplication> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TestRegularApplication> buildQueryWrapper(TestRegularApplicationBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TestRegularApplication> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(TestRegularApplication::getId);
        lqw.like(StringUtils.isNotBlank(bo.getName()), TestRegularApplication::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getDepartment()), TestRegularApplication::getDepartment, bo.getDepartment());
        lqw.eq(StringUtils.isNotBlank(bo.getPosition()), TestRegularApplication::getPosition, bo.getPosition());
        lqw.eq(StringUtils.isNotBlank(bo.getEmployeeType()), TestRegularApplication::getEmployeeType, bo.getEmployeeType());
        lqw.eq(bo.getEntryDate() != null, TestRegularApplication::getEntryDate, bo.getEntryDate());
        lqw.eq(StringUtils.isNotBlank(bo.getProbationPeriod()), TestRegularApplication::getProbationPeriod, bo.getProbationPeriod());
        lqw.eq(bo.getPlanRegularDate() != null, TestRegularApplication::getPlanRegularDate, bo.getPlanRegularDate());
        lqw.eq(bo.getActualRegularDate() != null, TestRegularApplication::getActualRegularDate, bo.getActualRegularDate());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), TestRegularApplication::getStatus, bo.getStatus());
        lqw.eq(bo.getFlowInstanceId() != null, TestRegularApplication::getFlowInstanceId, bo.getFlowInstanceId());
        lqw.eq(StringUtils.isNotBlank(bo.getApprovalOpinion()), TestRegularApplication::getApprovalOpinion, bo.getApprovalOpinion());
        lqw.eq(StringUtils.isNotBlank(bo.getFileUrl()), TestRegularApplication::getFileUrl, bo.getFileUrl());
        return lqw;
    }

    /**
     * 新增转正申请
     *
     * @param bo 转正申请
     * @return 是否新增成功
     */
    @Override
//    public Boolean insertByBo(TestRegularApplicationBo bo) {
//        TestRegularApplication add = MapstructUtils.convert(bo, TestRegularApplication.class);
//        validEntityBeforeSave(add);
//        boolean flag = baseMapper.insert(add) > 0;
//        if (flag) {
//            bo.setId(add.getId());
//        }
//        return flag;
//    }
    public TestRegularApplicationVo insertByBo(TestRegularApplicationBo bo) {
        TestRegularApplication add = MapstructUtils.convert(bo, TestRegularApplication.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (!flag) {
            throw new RuntimeException("新增失败");
        }
        bo.setId(add.getId());
        return MapstructUtils.convert(add, TestRegularApplicationVo.class); // 返回Vo
    }

    /**
     * 修改转正申请
     *
     * @param bo 转正申请
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TestRegularApplicationBo bo) {
        TestRegularApplication update = MapstructUtils.convert(bo, TestRegularApplication.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TestRegularApplication entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除转正申请信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
//        workflowService.deleteInstance(ids);
        return baseMapper.deleteByIds(ids) > 0;
    }

    /**
     * 总体流程监听(例如: 草稿，撤销，退回，作废，终止，已完成，单任务完成等)
     * 正常使用只需#processEvent.flowCode=='leave1'
     * 示例为了方便则使用startsWith匹配了全部示例key
     *
     * @param processEvent 参数
     */
    @EventListener(condition = "#processEvent.flowCode.startsWith('application')")
    public void processHandler(ProcessEvent processEvent) {
        log.info("当前任务执行了{}", processEvent.toString());
        TestRegularApplication testregularapplication = baseMapper.selectById(Long.valueOf(processEvent.getBusinessId()));
        testregularapplication.setStatus(processEvent.getStatus());
        // 用于例如审批附件 审批意见等 存储到业务表内 自行根据业务实现存储流程
        Map<String, Object> params = processEvent.getParams();
        if (MapUtil.isNotEmpty(params)) {
            // 历史任务扩展(通常为附件)
            String hisTaskExt = Convert.toStr(params.get("hisTaskExt"));
            // 办理人
            String handler = Convert.toStr(params.get("handler"));
            // 办理意见
            String message = Convert.toStr(params.get("message"));
        }
        if (processEvent.isSubmit()) {
            testregularapplication.setStatus(BusinessStatusEnum.WAITING.getStatus());
        }
        baseMapper.updateById(testregularapplication);
    }

    /**
     * 执行任务创建监听
     * 示例：也可通过  @EventListener(condition = "#processCreateTaskEvent.flowCode=='leave1'")进行判断
     * 在方法中判断流程节点key
     * if ("xxx".equals(processCreateTaskEvent.getNodeCode())) {
     * //执行业务逻辑
     * }
     *
     * @param processCreateTaskEvent 参数
     */
    @EventListener(condition = "#processCreateTaskEvent.flowCode.startsWith('application')")
    public void processCreateTaskHandler(ProcessCreateTaskEvent processCreateTaskEvent) {
        log.info("当前任务创建了{}", processCreateTaskEvent.toString());
        TestRegularApplication testregularapplication = baseMapper.selectById(Long.valueOf(processCreateTaskEvent.getBusinessId()));
        testregularapplication.setStatus(BusinessStatusEnum.WAITING.getStatus());
        baseMapper.updateById(testregularapplication);
    }

    /**
     * 监听删除流程事件
     * 正常使用只需#processDeleteEvent.flowCode=='leave1'
     * 示例为了方便则使用startsWith匹配了全部示例key
     *
     * @param processDeleteEvent 参数
     */
    @EventListener(condition = "#processDeleteEvent.flowCode.startsWith('application')")
    public void processDeleteHandler(ProcessDeleteEvent processDeleteEvent) {
        log.info("监听删除流程事件，当前任务执行了{}", processDeleteEvent.toString());
        TestRegularApplication testregularapplication = baseMapper.selectById(Long.valueOf(processDeleteEvent.getBusinessId()));
        if (ObjectUtil.isNull(testregularapplication)) {
            return;
        }
        baseMapper.deleteById(testregularapplication.getId());
    }

}
