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
import org.dromara.workflow.domain.bo.TestBusinessBo;
import org.dromara.workflow.domain.vo.TestBusinessVo;
import org.dromara.workflow.service.ITestBusinessService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出差
 *
 * @author may
 * @date 2023-07-21
 */
@ConditionalOnEnable
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/workflow/business")
public class TestBusinessController extends BaseController {

    private final ITestBusinessService testBusinessService;

    /**
     * 查询出差列表
     */
    @SaCheckPermission("workflow:business:list")
    @GetMapping("/list")
    public TableDataInfo<TestBusinessVo> list(TestBusinessBo bo, PageQuery pageQuery) {
        return testBusinessService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出出差列表
     */
    @SaCheckPermission("workflow:business:export")
    @Log(title = "出差", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TestBusinessBo bo, HttpServletResponse response) {
        List<TestBusinessVo> list = testBusinessService.queryList(bo);
        ExcelUtil.exportExcel(list, "出差", TestBusinessVo.class, response);
    }

    /**
     * 获取出差详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("workflow:business:query")
    @GetMapping("/{id}")
    public R<TestBusinessVo> getInfo(@NotNull(message = "主键不能为空")
                                  @PathVariable Long id) {
        return R.ok(testBusinessService.queryById(id));
    }

    /**
     * 新增出差
     */
    @SaCheckPermission("workflow:business:add")
    @Log(title = "出差", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<TestBusinessVo> add(@Validated(AddGroup.class) @RequestBody TestBusinessBo bo) {
        return R.ok(testBusinessService.insertByBo(bo));
    }

    /**
     * 修改出差
     */
    @SaCheckPermission("workflow:business:edit")
    @Log(title = "出差", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<TestBusinessVo> edit(@Validated(EditGroup.class) @RequestBody TestBusinessBo bo) {
        return R.ok(testBusinessService.updateByBo(bo));
    }

    /**
     * 删除出差
     *
     * @param ids 主键串
     */
    @SaCheckPermission("workflow:business:remove")
    @Log(title = "出差", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(testBusinessService.deleteWithValidByIds(List.of(ids)));
    }
}
