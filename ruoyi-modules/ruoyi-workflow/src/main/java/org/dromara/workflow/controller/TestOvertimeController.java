package org.dromara.workflow.controller;

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
import org.dromara.workflow.common.ConditionalOnEnable;
import org.dromara.workflow.domain.bo.TestOvertimeBo;
import org.dromara.workflow.domain.vo.TestOvertimeVo;
import org.dromara.workflow.service.ITestOvertimeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 请假
 *
 * @author may
 * @date 2023-07-21
 */
@ConditionalOnEnable
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/overtime")
public class TestOvertimeController extends BaseController {

    private final ITestOvertimeService testOvertimeService;

    /**
     * 查询请假列表
     */
    @SaCheckPermission(" ")
    @GetMapping("/list")
    public TableDataInfo<TestOvertimeVo> list(TestOvertimeBo bo, PageQuery pageQuery) {
        return testOvertimeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出请假列表
     */
    @SaCheckPermission("workflow:overtime:export")
    @Log(title = "加班", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TestOvertimeBo bo, HttpServletResponse response) {
        List<TestOvertimeVo> list = testOvertimeService.queryList(bo);
        ExcelUtil.exportExcel(list, "请假", TestOvertimeVo.class, response);
    }

    /**
     * 获取请假详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:overtime:query")
    @GetMapping("/{id}")
    public R<TestOvertimeVo> getInfo(@NotNull(message = "主键不能为空")
                                  @PathVariable Long id) {
        return R.ok(testOvertimeService.queryById(id));
    }

    /**
     * 新增请假
     */
    @SaCheckPermission("workflow:overtime:add")
    @Log(title = "加班", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<TestOvertimeVo> add(@Validated(AddGroup.class) @RequestBody TestOvertimeBo bo) {
        return R.ok(testOvertimeService.insertByBo(bo));
    }

    /**
     * 修改请假
     */
    @SaCheckPermission("workflow:overtime:edit")
    @Log(title = "加班", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<TestOvertimeVo> edit(@Validated(EditGroup.class) @RequestBody TestOvertimeBo bo) {
        return R.ok(testOvertimeService.updateByBo(bo));
    }

    /**
     * 删除请假
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:overtime:remove")
    @Log(title = "加班", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(testOvertimeService.deleteWithValidByIds(List.of(ids)));
    }
}
