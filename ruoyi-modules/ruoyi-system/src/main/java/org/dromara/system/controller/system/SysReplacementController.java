package org.dromara.system.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.system.domain.bo.SysReplacementBo;
import org.dromara.system.domain.vo.SysReplacementVo;
import org.dromara.system.service.ISysReplacementService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("system/replacement")
public class SysReplacementController extends BaseController {

    private final ISysReplacementService sysReplacementService;

    /**
     * 查询请假列表
     */
    @SaCheckPermission("system:replacement:list")
    @GetMapping("/list")
    public TableDataInfo<SysReplacementVo> list(SysReplacementBo bo, PageQuery pageQuery) {
        return sysReplacementService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出请假列表
     */
    @SaCheckPermission("system:replacement:export")
    @Log(title = "补卡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysReplacementBo bo, HttpServletResponse response) {
        List<SysReplacementVo> list = sysReplacementService.queryList(bo);
        ExcelUtil.exportExcel(list, "补卡", SysReplacementVo.class, response);
    }

    /**
     * 获取请假详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:replacement:query")
    @GetMapping("/{id}")
    public R<SysReplacementVo> getInfo(@NotNull(message = "主键不能为空")
                                  @PathVariable Long id) {
        return R.ok(sysReplacementService.queryById(id));
    }

    /**
     * 新增请假
     */
    @SaCheckPermission("system:replacement:add")
    @Log(title = "补卡", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<SysReplacementVo> add(@Validated(AddGroup.class) @RequestBody SysReplacementBo bo) {
        return R.ok(sysReplacementService.insertByBo(bo));
    }

    /**
     * 修改请假
     */
    @SaCheckPermission("system:replacement:edit")
    @Log(title = "补卡", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<SysReplacementVo> edit(@Validated(EditGroup.class) @RequestBody SysReplacementBo bo) {
        return R.ok(sysReplacementService.updateByBo(bo));
    }

    /**
     * 删除请假
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:replacement:remove")
    @Log(title = "补卡", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(sysReplacementService.deleteWithValidByIds(List.of(ids)));
    }
}
