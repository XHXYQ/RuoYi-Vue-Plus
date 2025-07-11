package org.dromara.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.dromara.system.domain.SysReplacement;
import org.dromara.system.domain.bo.SysReplacementBo;
import org.dromara.system.domain.vo.SysReplacementVo;
import org.dromara.system.mapper.SysReplacementMapper;
import org.dromara.system.service.ISysReplacementService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static cn.dev33.satoken.SaManager.log;


@RequiredArgsConstructor
@Service
@Slf4j
public class SysReplacementServiceImpl implements ISysReplacementService {

    private final SysReplacementMapper baseMapper;
    private final WorkflowService workflowService;

    /**
     * spel条件表达：判断小于2
     *
     * @param leaveDays 待判断的变量（可不传自行返回true或false）
     * @return boolean
     */
    public boolean eval(Integer leaveDays) {
        if (leaveDays <= 2) {
            return true;
        }
        return false;
    }

    /**
     * 查询请假
     */
    @Override
    public SysReplacementVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询请假列表
     */
    @Override
    public TableDataInfo<SysReplacementVo> queryPageList(SysReplacementBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysReplacement> lqw = buildQueryWrapper(bo);
        Page<SysReplacementVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询请假列表
     */
    @Override
    public List<SysReplacementVo> queryList(SysReplacementBo bo) {
        LambdaQueryWrapper<SysReplacement> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SysReplacement> buildQueryWrapper(SysReplacementBo bo) {
        LambdaQueryWrapper<SysReplacement> lqw = Wrappers.lambdaQuery();
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增请假
     */
    @Override
    public SysReplacementVo insertByBo(SysReplacementBo bo) {

        SysReplacement add = MapstructUtils.convert(bo, SysReplacement.class);
        if (StringUtils.isBlank(add.getStatus())) {
            add.setStatus(BusinessStatusEnum.DRAFT.getStatus());
        }
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return MapstructUtils.convert(add, SysReplacementVo.class);
    }

    /**
     * 修改请假
     */
    @Override
    public SysReplacementVo updateByBo(SysReplacementBo bo) {
        SysReplacement update = MapstructUtils.convert(bo, SysReplacement.class);
        baseMapper.updateById(update);
        return MapstructUtils.convert(update, SysReplacementVo.class);
    }

    /**
     * 批量删除请假
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(List<Long> ids) {
        workflowService.deleteInstance(ids);
        return baseMapper.deleteByIds(ids) > 0;
    }

    /**
     * 总体流程监听(例如: 草稿，撤销，退回，作废，终止，已完成，单任务完成等)
     * 正常使用只需#processEvent.flowCode=='leave1'
     * 示例为了方便则使用startsWith匹配了全部示例key
     *
     * @param processEvent 参数
     */
    @EventListener(condition = "#processEvent.flowCode.startsWith('replacement')")
    public void processHandler(ProcessEvent processEvent) {
        log.info("当前任务执行了{}", processEvent.toString());
        SysReplacement sysReplacement = baseMapper.selectById(Long.valueOf(processEvent.getBusinessId()));
        sysReplacement.setStatus(processEvent.getStatus());
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
            sysReplacement.setStatus(BusinessStatusEnum.WAITING.getStatus());
        }
        baseMapper.updateById(sysReplacement);
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
    @EventListener(condition = "#processCreateTaskEvent.flowCode.startsWith('leave')")
    public void processCreateTaskHandler(ProcessCreateTaskEvent processCreateTaskEvent) {
        log.info("当前任务创建了{}", processCreateTaskEvent.toString());
        SysReplacement sysReplacement = baseMapper.selectById(Long.valueOf(processCreateTaskEvent.getBusinessId()));
        sysReplacement.setStatus(BusinessStatusEnum.WAITING.getStatus());
        baseMapper.updateById(sysReplacement);
    }

    /**
     * 监听删除流程事件
     * 正常使用只需#processDeleteEvent.flowCode=='leave1'
     * 示例为了方便则使用startsWith匹配了全部示例key
     *
     * @param processDeleteEvent 参数
     */
    @EventListener(condition = "#processDeleteEvent.flowCode.startsWith('leave')")
    public void processDeleteHandler(ProcessDeleteEvent processDeleteEvent) {
        log.info("监听删除流程事件，当前任务执行了{}", processDeleteEvent.toString());
        SysReplacement sysReplacement = baseMapper.selectById(Long.valueOf(processDeleteEvent.getBusinessId()));
        if (ObjectUtil.isNull(sysReplacement)) {
            return;
        }
        baseMapper.deleteById(sysReplacement.getId());
    }
}
