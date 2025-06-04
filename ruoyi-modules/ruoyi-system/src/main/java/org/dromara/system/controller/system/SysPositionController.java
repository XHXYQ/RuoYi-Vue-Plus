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
import org.dromara.system.domain.vo.SysPositionVo;
import org.dromara.system.domain.bo.SysPositionBo;
import org.dromara.system.service.ISysPositionService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 系统职位管理
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/position")
public class SysPositionController extends BaseController {

    private final ISysPositionService sysPositionService;

    /**
     * 查询系统职位管理列表
     */
    @SaCheckPermission("system:position:list")
    @GetMapping("/list")
    public TableDataInfo<SysPositionVo> list(SysPositionBo bo, PageQuery pageQuery) {
        return sysPositionService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出系统职位管理列表
     */
    @SaCheckPermission("system:position:export")
    @Log(title = "系统职位管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysPositionBo bo, HttpServletResponse response) {
        List<SysPositionVo> list = sysPositionService.queryList(bo);
        ExcelUtil.exportExcel(list, "系统职位管理", SysPositionVo.class, response);
    }

    /**
     * 获取系统职位管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:position:query")
    @GetMapping("/{id}")
    public R<SysPositionVo> getInfo(@NotNull(message = "主键不能为空")
                                    @PathVariable Long id) {
        return R.ok(sysPositionService.queryById(id));
    }

    /**
     * 新增系统职位管理
     */
    @SaCheckPermission("system:position:add")
    @Log(title = "系统职位管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysPositionBo bo) {
        return toAjax(sysPositionService.insertByBo(bo));
    }

    /**
     * 修改系统职位管理
     */
    @SaCheckPermission("system:position:edit")
    @Log(title = "系统职位管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysPositionBo bo) {
        return toAjax(sysPositionService.updateByBo(bo));
    }

    /**
     * 删除系统职位管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:position:remove")
    @Log(title = "系统职位管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(sysPositionService.deleteWithValidByIds(List.of(ids), true));
    }
}
