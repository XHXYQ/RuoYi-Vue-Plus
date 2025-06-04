package org.dromara.workflow.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.event.ProcessCreateTaskEvent;
import org.dromara.common.core.domain.event.ProcessDeleteEvent;
import org.dromara.common.core.domain.event.ProcessEvent;
import org.dromara.common.core.enums.BusinessStatusEnum;
import org.dromara.common.core.service.WorkflowService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.workflow.common.ConditionalOnEnable;
import org.dromara.workflow.domain.TestLeave;
import org.dromara.workflow.domain.TestRegularApplication;
import org.dromara.workflow.domain.bo.TestRegularApplicationBo;
import org.dromara.workflow.domain.vo.TestRegularApplicationVo;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.dromara.workflow.domain.bo.TestOutgoingBo;
import org.dromara.workflow.domain.vo.TestOutgoingVo;
import org.dromara.workflow.domain.TestOutgoing;
import org.dromara.workflow.mapper.TestOutgoingMapper;
import org.dromara.workflow.service.ITestOutgoingService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 外出申请Service业务层处理
 *
 * @author Tim
 * @date 2025-05-28
 */
@ConditionalOnEnable
@RequiredArgsConstructor
@Service
@Slf4j
public class TestOutgoingServiceImpl implements ITestOutgoingService {

    private final TestOutgoingMapper baseMapper;
    private final WorkflowService workflowService;

    /**
     * 查询外出申请
     *
     * @param id 主键
     * @return 外出申请
     */
    @Override
    public TestOutgoingVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询外出申请列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 外出申请分页列表
     */
    @Override
    public TableDataInfo<TestOutgoingVo> queryPageList(TestOutgoingBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TestOutgoing> lqw = buildQueryWrapper(bo);
        Page<TestOutgoingVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的外出申请列表
     *
     * @param bo 查询条件
     * @return 外出申请列表
     */
    @Override
    public List<TestOutgoingVo> queryList(TestOutgoingBo bo) {
        LambdaQueryWrapper<TestOutgoing> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TestOutgoing> buildQueryWrapper(TestOutgoingBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TestOutgoing> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(TestOutgoing::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getApplicant()), TestOutgoing::getApplicant, bo.getApplicant());
        lqw.eq(bo.getStartTime() != null, TestOutgoing::getStartTime, bo.getStartTime());
        lqw.eq(bo.getEndTime() != null, TestOutgoing::getEndTime, bo.getEndTime());
        lqw.eq(bo.getLeaveDay() != null, TestOutgoing::getLeaveDay, bo.getLeaveDay());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), TestOutgoing::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增外出申请
     *
     * @param bo 外出申请
     * @return 是否新增成功
     */
    @Override
//    public Boolean insertByBo(TestOutgoingBo bo) {
//        TestOutgoing add = MapstructUtils.convert(bo, TestOutgoing.class);
//        validEntityBeforeSave(add);
//        boolean flag = baseMapper.insert(add) > 0;
//        if (flag) {
//            bo.setId(add.getId());
//        }
//        return flag;
//    }
    public TestOutgoingVo insertByBo(TestOutgoingBo bo) {
        TestOutgoing add = MapstructUtils.convert(bo, TestOutgoing.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (!flag) {
            throw new RuntimeException("新增失败");
        }
        bo.setId(add.getId());
        return MapstructUtils.convert(add, TestOutgoingVo.class); // 返回Vo
    }

    /**
     * 修改外出申请
     *
     * @param bo 外出申请
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TestOutgoingBo bo) {
        TestOutgoing update = MapstructUtils.convert(bo, TestOutgoing.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TestOutgoing entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除外出申请信息
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


    /**
     * 总体流程监听(例如: 草稿，撤销，退回，作废，终止，已完成，单任务完成等)
     * 正常使用只需#processEvent.flowCode=='leave1'
     * 示例为了方便则使用startsWith匹配了全部示例key
     *
     * @param processEvent 参数
     */
    @EventListener(condition = "#processEvent.flowCode.startsWith('outgoing')")
    public void processHandler(ProcessEvent processEvent) {
        log.info("当前任务执行了{}", processEvent.toString());
        TestOutgoing testOutgoing = baseMapper.selectById(Long.valueOf(processEvent.getBusinessId()));
        testOutgoing.setStatus(processEvent.getStatus());
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
            testOutgoing.setStatus(BusinessStatusEnum.WAITING.getStatus());
        }
        baseMapper.updateById(testOutgoing);
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
    @EventListener(condition = "#processCreateTaskEvent.flowCode.startsWith('outgoing')")
    public void processCreateTaskHandler(ProcessCreateTaskEvent processCreateTaskEvent) {
        log.info("当前任务创建了{}", processCreateTaskEvent.toString());
        TestOutgoing testOutgoing = baseMapper.selectById(Long.valueOf(processCreateTaskEvent.getBusinessId()));
        testOutgoing.setStatus(BusinessStatusEnum.WAITING.getStatus());
        baseMapper.updateById(testOutgoing);
    }

    /**
     * 监听删除流程事件
     * 正常使用只需#processDeleteEvent.flowCode=='leave1'
     * 示例为了方便则使用startsWith匹配了全部示例key
     *
     * @param processDeleteEvent 参数
     */
    @EventListener(condition = "#processDeleteEvent.flowCode.startsWith('outgoing')")
    public void processDeleteHandler(ProcessDeleteEvent processDeleteEvent) {
        log.info("监听删除流程事件，当前任务执行了{}", processDeleteEvent.toString());
        TestOutgoing testOutgoing = baseMapper.selectById(Long.valueOf(processDeleteEvent.getBusinessId()));
        if (ObjectUtil.isNull(testOutgoing)) {
            return;
        }
        baseMapper.deleteById(testOutgoing.getId());
    }
}
