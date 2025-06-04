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
import org.dromara.system.domain.vo.SysCompanyManagementVo;
import org.dromara.system.domain.bo.SysCompanyManagementBo;
import org.dromara.system.service.ISysCompanyManagementService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 公司组织管理
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/organization")
public class SysCompanyManagementController extends BaseController {

    private final ISysCompanyManagementService sysCompanyManagementService;

    /**
     * 查询公司组织管理列表
     */
    @SaCheckPermission("system:organization:list")
    @GetMapping("/list")
    public TableDataInfo<SysCompanyManagementVo> list(SysCompanyManagementBo bo, PageQuery pageQuery) {
        return sysCompanyManagementService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出公司组织管理列表
     */
    @SaCheckPermission("system:organization:export")
    @Log(title = "公司组织管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysCompanyManagementBo bo, HttpServletResponse response) {
        List<SysCompanyManagementVo> list = sysCompanyManagementService.queryList(bo);
        ExcelUtil.exportExcel(list, "公司组织管理", SysCompanyManagementVo.class, response);
    }

    /**
     * 获取公司组织管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:organization:query")
    @GetMapping("/{id}")
    public R<SysCompanyManagementVo> getInfo(@NotNull(message = "主键不能为空")
                                             @PathVariable Long id) {
        return R.ok(sysCompanyManagementService.queryById(id));
    }

    /**
     * 新增公司组织管理
     */
    @SaCheckPermission("system:organization:add")
    @Log(title = "公司组织管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysCompanyManagementBo bo) {
        return toAjax(sysCompanyManagementService.insertByBo(bo));
    }

    /**
     * 修改公司组织管理
     */
    @SaCheckPermission("system:organization:edit")
    @Log(title = "公司组织管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysCompanyManagementBo bo) {
        return toAjax(sysCompanyManagementService.updateByBo(bo));
    }

    /**
     * 删除公司组织管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:organization:remove")
    @Log(title = "公司组织管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(sysCompanyManagementService.deleteWithValidByIds(List.of(ids), true));
    }
}
