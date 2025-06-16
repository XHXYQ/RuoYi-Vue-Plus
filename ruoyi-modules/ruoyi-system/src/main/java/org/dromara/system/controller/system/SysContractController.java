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
import org.dromara.system.domain.bo.SysContractBo;
import org.dromara.system.domain.vo.SysContractVo;
import org.dromara.system.service.ISysContractService;
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
@RequestMapping("system/contract")
public class SysContractController extends BaseController {

    private final ISysContractService contractService;

    /**
     * 添加员工
     */
    @Log(title = "合同管理", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysContractBo bo) {
        return toAjax(contractService.insertContractAndUser(bo));
    }

    /**
     * 修改员工信息
     */
    @Log(title = "合同管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysContractBo bo) {
        return toAjax(contractService.updateContract(bo));
    }

    /**
     * 删除员工
     */
    @Log(title = "合同管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(contractService.deleteContractByIds(ids));
    }

    /**
     * 获取员工详细信息
     */
    @GetMapping("/{id}")
    public R<SysContractVo> getInfo(@PathVariable Long id) {
        return R.ok(contractService.selectContractById(id));
    }

    /**
     * 查询员工列表（分页）
     */
    @GetMapping("/list")
    public TableDataInfo<SysContractVo> list(SysContractBo bo, PageQuery pageQuery) {
        return contractService.selectPageContractList(bo, pageQuery);
    }

    /**
     * 导出员工花名册Excel
     */
    @Log(title = "合同管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysContractBo bo) {
        List<SysContractVo> list = contractService.selectContractList(bo);
        ExcelUtil.exportExcel(list, "员工花名册", SysContractVo.class, response);
    }

}


