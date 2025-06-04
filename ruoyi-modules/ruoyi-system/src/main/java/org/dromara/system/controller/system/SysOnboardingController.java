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
import org.dromara.system.domain.vo.SysOnboardingVo;
import org.dromara.system.domain.bo.SysOnboardingBo;
import org.dromara.system.service.ISysOnboardingService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 员工入职申请
 *
 * @author Tim
 * @date 2025-06-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/onboarding")
public class SysOnboardingController extends BaseController {

    private final ISysOnboardingService sysOnboardingService;

    /**
     * 查询员工入职申请列表
     */
    @SaCheckPermission("system:onboarding:list")
    @GetMapping("/list")
    public TableDataInfo<SysOnboardingVo> list(SysOnboardingBo bo, PageQuery pageQuery) {
        return sysOnboardingService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出员工入职申请列表
     */
    @SaCheckPermission("system:onboarding:export")
    @Log(title = "员工入职申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysOnboardingBo bo, HttpServletResponse response) {
        List<SysOnboardingVo> list = sysOnboardingService.queryList(bo);
        ExcelUtil.exportExcel(list, "员工入职申请", SysOnboardingVo.class, response);
    }

    /**
     * 获取员工入职申请详细信息
     *
     * @param onboardingId 主键
     */
    @SaCheckPermission("system:onboarding:query")
    @GetMapping("/{onboardingId}")
    public R<SysOnboardingVo> getInfo(@NotNull(message = "主键不能为空")
                                      @PathVariable Long onboardingId) {
        return R.ok(sysOnboardingService.queryById(onboardingId));
    }

    /**
     * 新增员工入职申请
     */
    @SaCheckPermission("system:onboarding:add")
    @Log(title = "员工入职申请", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysOnboardingBo bo) {
        return toAjax(sysOnboardingService.insertByBo(bo));
    }

    /**
     * 修改员工入职申请
     */
    @SaCheckPermission("system:onboarding:edit")
    @Log(title = "员工入职申请", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysOnboardingBo bo) {
        return toAjax(sysOnboardingService.updateByBo(bo));
    }

    /**
     * 删除员工入职申请
     *
     * @param onboardingIds 主键串
     */
    @SaCheckPermission("system:onboarding:remove")
    @Log(title = "员工入职申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{onboardingIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] onboardingIds) {
        return toAjax(sysOnboardingService.deleteWithValidByIds(List.of(onboardingIds), true));
    }
}
