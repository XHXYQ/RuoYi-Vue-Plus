package org.dromara.system.controller.system;

import java.util.List;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.system.domain.vo.AttRuleOvertimeVo;
import org.dromara.system.domain.bo.AttRuleOvertimeBo;
import org.dromara.system.service.IAttRuleOvertimeService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import static cn.dev33.satoken.SaManager.log;

/**
 * 加班规则
 *
 * @author Skye
 * @date 2025-06-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/ruleOvertime")
public class AttRuleOvertimeController extends BaseController {

    private final IAttRuleOvertimeService attRuleOvertimeService;

    /**
     * 查询加班规则列表
     */
    @SaCheckPermission("system:ruleOvertime:list")
    @GetMapping("/list")
    public TableDataInfo<AttRuleOvertimeVo> list(AttRuleOvertimeBo bo, PageQuery pageQuery) {
        return attRuleOvertimeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出加班规则列表
     */
    @SaCheckPermission("system:ruleOvertime:export")
    @Log(title = "加班规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AttRuleOvertimeBo bo, HttpServletResponse response) {
        List<AttRuleOvertimeVo> list = attRuleOvertimeService.queryList(bo);
        ExcelUtil.exportExcel(list, "加班规则", AttRuleOvertimeVo.class, response);
    }

    /**
     * 获取加班规则详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:ruleOvertime:query")
    @GetMapping("/{id}")
    public R<AttRuleOvertimeVo> getInfo(@NotNull(message = "主键不能为空")
                                        @PathVariable Long id) {
        return R.ok(attRuleOvertimeService.queryById(id));
    }

    /**
     * 新增加班规则
     */
    @SaCheckPermission("system:ruleOvertime:add")
    @Log(title = "加班规则", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AttRuleOvertimeBo bo) {
        return toAjax(attRuleOvertimeService.insertByBo(bo));
    }

    /**
     * 修改加班规则
     */
    @SaCheckPermission("system:ruleOvertime:edit")
    @Log(title = "加班规则", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AttRuleOvertimeBo bo) {
        return toAjax(attRuleOvertimeService.updateByBo(bo));
    }

    /**
     * 删除加班规则
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:ruleOvertime:remove")
    @Log(title = "加班规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(attRuleOvertimeService.deleteWithValidByIds(List.of(ids), true));
    }
}
