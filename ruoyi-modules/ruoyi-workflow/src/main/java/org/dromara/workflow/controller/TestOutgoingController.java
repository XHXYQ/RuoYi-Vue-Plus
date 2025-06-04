package org.dromara.workflow.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.workflow.domain.bo.TestRegularApplicationBo;
import org.dromara.workflow.domain.vo.TestRegularApplicationVo;
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
import org.dromara.workflow.domain.vo.TestOutgoingVo;
import org.dromara.workflow.domain.bo.TestOutgoingBo;
import org.dromara.workflow.service.ITestOutgoingService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 外出申请
 *
 * @author Tim
 * @date 2025-05-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/outgoing")
public class TestOutgoingController extends BaseController {

    private final ITestOutgoingService testOutgoingService;

    /**
     * 查询外出申请列表
     */
    @SaCheckPermission("workflow:outgoing:list")
    @GetMapping("/list")
    public TableDataInfo<TestOutgoingVo> list(TestOutgoingBo bo, PageQuery pageQuery) {
        return testOutgoingService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出外出申请列表
     */
    @SaCheckPermission("workflow:outgoing:export")
    @Log(title = "外出申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TestOutgoingBo bo, HttpServletResponse response) {
        List<TestOutgoingVo> list = testOutgoingService.queryList(bo);
        ExcelUtil.exportExcel(list, "外出申请", TestOutgoingVo.class, response);
    }

    /**
     * 获取外出申请详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:outgoing:query")
    @GetMapping("/{id}")
    public R<TestOutgoingVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(testOutgoingService.queryById(id));
    }

    /**
     * 新增外出申请
     */
    @SaCheckPermission("workflow:outgoing:add")
    @Log(title = "外出申请", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
//    public R<Void> add(@Validated(AddGroup.class) @RequestBody TestOutgoingBo bo) {
//        return toAjax(testOutgoingService.insertByBo(bo));
//    }
    public R<TestOutgoingVo> add(@RequestBody TestOutgoingBo bo) {
        return R.ok(testOutgoingService.insertByBo(bo));
    }


    /**
     * 修改外出申请
     */
    @SaCheckPermission("workflow:outgoing:edit")
    @Log(title = "外出申请", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TestOutgoingBo bo) {
        return toAjax(testOutgoingService.updateByBo(bo));
    }

    /**
     * 删除外出申请
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:outgoing:remove")
    @Log(title = "外出申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(testOutgoingService.deleteWithValidByIds(List.of(ids), true));
    }
}
