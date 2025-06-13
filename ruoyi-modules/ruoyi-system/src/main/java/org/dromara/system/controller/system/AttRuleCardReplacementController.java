package org.dromara.system.controller;

import java.util.List;

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
import org.dromara.system.domain.vo.AttRuleCardReplacementVo;
import org.dromara.system.domain.bo.AttRuleCardReplacementBo;
import org.dromara.system.service.IAttRuleCardReplacementService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 补卡规则
 *
 * @author Skye
 * @date 2025-06-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/ruleCardReplacement")
public class AttRuleCardReplacementController extends BaseController {

    private final IAttRuleCardReplacementService attRuleCardReplacementService;

    /**
     * 查询补卡规则列表
     */
    @SaCheckPermission("system:ruleCardReplacement:list")
    @GetMapping("/list")
    public TableDataInfo<AttRuleCardReplacementVo> list(AttRuleCardReplacementBo bo, PageQuery pageQuery) {
        return attRuleCardReplacementService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出补卡规则列表
     */
    @SaCheckPermission("system:ruleCardReplacement:export")
    @Log(title = "补卡规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AttRuleCardReplacementBo bo, HttpServletResponse response) {
        List<AttRuleCardReplacementVo> list = attRuleCardReplacementService.queryList(bo);
        ExcelUtil.exportExcel(list, "补卡规则", AttRuleCardReplacementVo.class, response);
    }

    /**
     * 获取补卡规则详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:ruleCardReplacement:query")
    @GetMapping("/{id}")
    public R<AttRuleCardReplacementVo> getInfo(@NotNull(message = "主键不能为空")
                                               @PathVariable Long id) {
        return R.ok(attRuleCardReplacementService.queryById(id));
    }

    /**
     * 新增补卡规则
     */
    @SaCheckPermission("system:ruleCardReplacement:add")
    @Log(title = "补卡规则", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AttRuleCardReplacementBo bo) {
        return toAjax(attRuleCardReplacementService.insertByBo(bo));
    }

    /**
     * 修改补卡规则
     */
    @SaCheckPermission("system:ruleCardReplacement:edit")
    @Log(title = "补卡规则", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AttRuleCardReplacementBo bo) {
        return toAjax(attRuleCardReplacementService.updateByBo(bo));
    }

    /**
     * 删除补卡规则
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:ruleCardReplacement:remove")
    @Log(title = "补卡规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(attRuleCardReplacementService.deleteWithValidByIds(List.of(ids), true));
    }
}
