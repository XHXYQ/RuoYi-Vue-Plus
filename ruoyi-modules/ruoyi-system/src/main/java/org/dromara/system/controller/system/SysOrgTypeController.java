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
import org.dromara.system.domain.vo.SysOrgTypeVo;
import org.dromara.system.domain.bo.SysOrgTypeBo;
import org.dromara.system.service.ISysOrgTypeService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 组织类型
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/orgType")
public class SysOrgTypeController extends BaseController {

    private final ISysOrgTypeService sysOrgTypeService;

    /**
     * 查询组织类型列表
     */
    @SaCheckPermission("system:orgType:list")
    @GetMapping("/list")
    public TableDataInfo<SysOrgTypeVo> list(SysOrgTypeBo bo, PageQuery pageQuery) {
        return sysOrgTypeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出组织类型列表
     */
    @SaCheckPermission("system:orgType:export")
    @Log(title = "组织类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysOrgTypeBo bo, HttpServletResponse response) {
        List<SysOrgTypeVo> list = sysOrgTypeService.queryList(bo);
        ExcelUtil.exportExcel(list, "组织类型", SysOrgTypeVo.class, response);
    }

    /**
     * 获取组织类型详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:orgType:query")
    @GetMapping("/{id}")
    public R<SysOrgTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                   @PathVariable Long id) {
        return R.ok(sysOrgTypeService.queryById(id));
    }

    /**
     * 新增组织类型
     */
    @SaCheckPermission("system:orgType:add")
    @Log(title = "组织类型", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysOrgTypeBo bo) {
        return toAjax(sysOrgTypeService.insertByBo(bo));
    }

    /**
     * 修改组织类型
     */
    @SaCheckPermission("system:orgType:edit")
    @Log(title = "组织类型", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysOrgTypeBo bo) {
        return toAjax(sysOrgTypeService.updateByBo(bo));
    }

    /**
     * 删除组织类型
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:orgType:remove")
    @Log(title = "组织类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(sysOrgTypeService.deleteWithValidByIds(List.of(ids), true));
    }
}
