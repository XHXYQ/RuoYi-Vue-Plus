package org.dromara.system.controller.system;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.system.domain.bo.SysEmployeeBo;
import org.dromara.system.domain.vo.EmployeeStatsVo;
import org.dromara.system.domain.vo.SysEmployeeVo;
import org.dromara.system.service.ISysEmployeeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * 员工信息管理
 *
 * @author [您的名字]
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("system/employee")
public class SysEmployeeController extends BaseController {

    private final ISysEmployeeService employeeService;

    /**
     * 添加员工
     */
    @Log(title = "员工管理", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysEmployeeBo bo) {
        return toAjax(employeeService.insertEmployeeAndUser(bo)); // 修改方法名
    }

    /**
     * 修改员工信息
     */
    @Log(title = "员工管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysEmployeeBo bo) {
        return toAjax(employeeService.updateEmployee(bo));
    }

    /**
     * 删除员工
     */
    @Log(title = "员工管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(employeeService.deleteEmployeeByIds(ids));
    }

    /**
     * 获取员工详细信息
     */
    @GetMapping("/{id}")
    public R<SysEmployeeVo> getInfo(@PathVariable Long id) {
        return R.ok(employeeService.selectEmployeeById(id));
    }

    /**
     * 查询员工列表（分页）
     */
    @GetMapping("/list")
    public TableDataInfo<SysEmployeeVo> list(SysEmployeeBo bo, PageQuery pageQuery) {
        return employeeService.selectPageEmployeeList(bo, pageQuery);
    }

    /**
     * 导出员工花名册Excel
     */
    @Log(title = "员工管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysEmployeeBo bo) {
        List<SysEmployeeVo> list = employeeService.selectEmployeeList(bo);
        ExcelUtil.exportExcel(list, "员工花名册", SysEmployeeVo.class, response);
    }

    /**
     * 获取在职员工统计
     */
    @GetMapping("/stats/active")
    public R<Long> getActiveEmployeeCount() {
        return R.ok(employeeService.getActiveEmployeeCount());
    }

    @GetMapping("/stats")
    public R<EmployeeStatsVo> stats() {
        return R.ok(employeeService.getEmployeeStats());
    }
}

