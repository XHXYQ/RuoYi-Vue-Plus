package org.dromara.workflow.controller;

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
import org.dromara.workflow.domain.vo.TestRegularApplicationVo;
import org.dromara.workflow.domain.bo.TestRegularApplicationBo;
import org.dromara.workflow.service.ITestRegularApplicationService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 转正申请
 *
 * @author Tim
 * @date 2025-05-24
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/regularApplication")
public class TestRegularApplicationController extends BaseController {

    private final ITestRegularApplicationService testRegularApplicationService;

    /**
     * 查询转正申请列表
     */
    @SaCheckPermission("workflow:regularApplication:list")
    @GetMapping("/list")
    public TableDataInfo<TestRegularApplicationVo> list(TestRegularApplicationBo bo, PageQuery pageQuery) {
        return testRegularApplicationService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出转正申请列表
     */
    @SaCheckPermission("workflow:regularApplication:export")
    @Log(title = "转正申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TestRegularApplicationBo bo, HttpServletResponse response) {
        List<TestRegularApplicationVo> list = testRegularApplicationService.queryList(bo);
        ExcelUtil.exportExcel(list, "转正申请", TestRegularApplicationVo.class, response);
    }

    /**
     * 获取转正申请详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:regularApplication:query")
    @GetMapping("/{id}")
    public R<TestRegularApplicationVo> getInfo(@NotNull(message = "主键不能为空")
                                               @PathVariable Long id) {
        return R.ok(testRegularApplicationService.queryById(id));
    }

    /**
     * 新增转正申请
     */
    @SaCheckPermission("workflow:regularApplication:add")
    @Log(title = "转正申请", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
//    public R<Void> add(@Validated(AddGroup.class) @RequestBody TestRegularApplicationBo bo) {
//        return toAjax(testRegularApplicationService.insertByBo(bo));
//    }
    public R<TestRegularApplicationVo> add(@RequestBody TestRegularApplicationBo bo) {
        return R.ok(testRegularApplicationService.insertByBo(bo));
    }

    /**
     * 修改转正申请
     */
    @SaCheckPermission("workflow:regularApplication:edit")
    @Log(title = "转正申请", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TestRegularApplicationBo bo) {
        return toAjax(testRegularApplicationService.updateByBo(bo));
    }

    /**
     * 删除转正申请
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:regularApplication:remove")
    @Log(title = "转正申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(testRegularApplicationService.deleteWithValidByIds(List.of(ids), true));
    }
}
