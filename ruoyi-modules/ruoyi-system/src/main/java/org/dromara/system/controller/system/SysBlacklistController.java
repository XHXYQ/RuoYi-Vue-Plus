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
import org.dromara.system.domain.vo.SysBlacklistVo;
import org.dromara.system.domain.bo.SysBlacklistBo;
import org.dromara.system.service.ISysBlacklistService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 系统黑名单
 *
 * @author Tim
 * @date 2025-06-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/blacklist")
public class SysBlacklistController extends BaseController {

    private final ISysBlacklistService sysBlacklistService;

    /**
     * 查询系统黑名单列表
     */
    @SaCheckPermission("system:blacklist:list")
    @GetMapping("/list")
    public TableDataInfo<SysBlacklistVo> list(SysBlacklistBo bo, PageQuery pageQuery) {
        return sysBlacklistService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出系统黑名单列表
     */
    @SaCheckPermission("system:blacklist:export")
    @Log(title = "系统黑名单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysBlacklistBo bo, HttpServletResponse response) {
        List<SysBlacklistVo> list = sysBlacklistService.queryList(bo);
        ExcelUtil.exportExcel(list, "系统黑名单", SysBlacklistVo.class, response);
    }

    /**
     * 获取系统黑名单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:blacklist:query")
    @GetMapping("/{id}")
    public R<SysBlacklistVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(sysBlacklistService.queryById(id));
    }

    /**
     * 新增系统黑名单
     */
    @SaCheckPermission("system:blacklist:add")
    @Log(title = "系统黑名单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysBlacklistBo bo) {
        return toAjax(sysBlacklistService.insertByBo(bo));
    }

    /**
     * 修改系统黑名单
     */
    @SaCheckPermission("system:blacklist:edit")
    @Log(title = "系统黑名单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysBlacklistBo bo) {
        return toAjax(sysBlacklistService.updateByBo(bo));
    }

    /**
     * 删除系统黑名单
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:blacklist:remove")
    @Log(title = "系统黑名单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(sysBlacklistService.deleteWithValidByIds(List.of(ids), true));
    }
}
