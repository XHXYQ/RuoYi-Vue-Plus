package org.dromara.system.controller.system;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.system.domain.SysResign;
import org.dromara.system.domain.bo.SysResignBo;
import org.dromara.system.domain.vo.SysResignVo;
import org.dromara.system.service.ISysResignService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("system/resign")
public class SysResignController extends BaseController {

    private final ISysResignService resignService;

    /**
     * 添加员工
     */
    @Log(title = "离职管理", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysResignBo bo) {
        return toAjax(resignService.insertResignAndUser(bo)); // 修改方法名
    }

    /**
     * 修改员工信息
     */
    @Log(title = "离职管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysResignBo bo) {
        return toAjax(resignService.updateResign(bo));
    }

    /**
     * 删除员工
     */
    @Log(title = "离职管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(resignService.deleteResignByIds(ids));
    }

    /**
     * 获取员工详细信息
     */
    @GetMapping("/{id}")
    public R<SysResignVo> getInfo(@PathVariable Long id) {
        return R.ok(resignService.selectResignById(id));
    }

    /**
     * 查询员工列表（分页）
     */
    @GetMapping("/list")
    public TableDataInfo<SysResignVo> list(SysResignBo bo, PageQuery pageQuery) {
        return resignService.selectPageResignList(bo, pageQuery);
    }

    /**
     * 导出员工花名册Excel
     */
    @Log(title = "离职管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysResignBo bo) {
        List<SysResignVo> list = resignService.selectResignList(bo);
        ExcelUtil.exportExcel(list, "员工花名册", SysResignVo.class, response);
    }
}
