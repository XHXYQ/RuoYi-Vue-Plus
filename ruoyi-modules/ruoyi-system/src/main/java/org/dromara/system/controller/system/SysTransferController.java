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
import org.dromara.system.domain.bo.SysTransferBo;
import org.dromara.system.domain.vo.SysTransferVo;
import org.dromara.system.service.ISysTransferService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("system/transfer")
public class SysTransferController extends BaseController {

    private final ISysTransferService sysTransferService;

    /**
     * 查询请假列表
     */
    @SaCheckPermission("system:transfer:list")
    @GetMapping("/list")
    public TableDataInfo<SysTransferVo> list(SysTransferBo bo, PageQuery pageQuery) {
        return sysTransferService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出请假列表
     */
    @SaCheckPermission("system:transfer:export")
    @Log(title = "调岗", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysTransferBo bo, HttpServletResponse response) {
        List<SysTransferVo> list = sysTransferService.queryList(bo);
        ExcelUtil.exportExcel(list, "调岗", SysTransferVo.class, response);
    }

    /**
     * 获取请假详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:transfer:query")
    @GetMapping("/{id}")
    public R<SysTransferVo> getInfo(@NotNull(message = "主键不能为空")
                                       @PathVariable Long id) {
        return R.ok(sysTransferService.queryById(id));
    }

    /**
     * 新增请假
     */
    @SaCheckPermission("system:transfer:add")
    @Log(title = "调岗", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<SysTransferVo> add(@Validated(AddGroup.class) @RequestBody SysTransferBo bo) {
        return R.ok(sysTransferService.insertByBo(bo));
    }

    /**
     * 修改请假
     */
    @SaCheckPermission("system:transfer:edit")
    @Log(title = "调岗", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<SysTransferVo> edit(@Validated(EditGroup.class) @RequestBody SysTransferBo bo) {
        return R.ok(sysTransferService.updateByBo(bo));
    }

    /**
     * 删除请假
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:transfer:remove")
    @Log(title = "调岗", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(sysTransferService.deleteWithValidByIds(List.of(ids)));
    }

}
