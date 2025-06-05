package org.dromara.system.controller.system;

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
import org.dromara.system.domain.vo.SysSolicitudeRuleVo;
import org.dromara.system.domain.bo.SysSolicitudeRuleBo;
import org.dromara.system.service.ISysSolicitudeRuleService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 员工关怀规则配置
 *
 * @author Tim
 * @date 2025-06-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/solicitudeRule")
public class SysSolicitudeRuleController extends BaseController {

    private final ISysSolicitudeRuleService sysSolicitudeRuleService;

    /**
     * 查询员工关怀规则配置列表
     */
    @SaCheckPermission("system:solicitudeRule:list")
    @GetMapping("/list")
    public TableDataInfo<SysSolicitudeRuleVo> list(SysSolicitudeRuleBo bo, PageQuery pageQuery) {
        return sysSolicitudeRuleService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出员工关怀规则配置列表
     */
    @SaCheckPermission("system:solicitudeRule:export")
    @Log(title = "员工关怀规则配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysSolicitudeRuleBo bo, HttpServletResponse response) {
        List<SysSolicitudeRuleVo> list = sysSolicitudeRuleService.queryList(bo);
        ExcelUtil.exportExcel(list, "员工关怀规则配置", SysSolicitudeRuleVo.class, response);
    }

    /**
     * 获取员工关怀规则配置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:solicitudeRule:query")
    @GetMapping("/{id}")
    public R<SysSolicitudeRuleVo> getInfo(@NotNull(message = "主键不能为空")
                                          @PathVariable Long id) {
        return R.ok(sysSolicitudeRuleService.queryById(id));
    }

    /**
     * 新增员工关怀规则配置
     */
    @SaCheckPermission("system:solicitudeRule:add")
    @Log(title = "员工关怀规则配置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysSolicitudeRuleBo bo) {
        return toAjax(sysSolicitudeRuleService.insertByBo(bo));
    }

    /**
     * 修改员工关怀规则配置
     */
    @SaCheckPermission("system:solicitudeRule:edit")
    @Log(title = "员工关怀规则配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysSolicitudeRuleBo bo) {
        return toAjax(sysSolicitudeRuleService.updateByBo(bo));
    }

    /**
     * 删除员工关怀规则配置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:solicitudeRule:remove")
    @Log(title = "员工关怀规则配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(sysSolicitudeRuleService.deleteWithValidByIds(List.of(ids), true));
    }
}
