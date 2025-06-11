package org.dromara.system.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.system.domain.bo.SysHolidayBo;
import org.dromara.system.domain.vo.SysHolidayVo;
import org.dromara.system.service.ISysHolidayService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.annotation.Validated;
import lombok.RequiredArgsConstructor;
import org.dromara.common.log.enums.BusinessType;


import java.util.Collections;
import java.util.List;

/**
 * 假期规则控制器
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("system/holiday")
public class SysHolidayController extends BaseController {

    private final ISysHolidayService holidayService;

    /**
     * 添加员工
     */
    @Log(title = "假期管理", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysHolidayBo bo) {
        return toAjax(holidayService.insertHolidayAndUser(bo));
    }

    /**
     * 修改员工信息
     */
    @Log(title = "假期管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysHolidayBo bo) {
        return toAjax(holidayService.updateHoliday(bo));
    }

    /**
     * 删除员工
     */
    @Log(title = "假期管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(holidayService.deleteHolidayByIds(ids));
    }

    /**
     * 获取员工详细信息
     */
    @GetMapping("/{id}")
    public R<SysHolidayVo> getInfo(@PathVariable Long id) {
        return R.ok(holidayService.selectHolidayById(id));
    }

    /**
     * 查询员工列表（分页）
     */
    @GetMapping("/list")
    public TableDataInfo<SysHolidayVo> list(SysHolidayBo bo, PageQuery pageQuery) {
        return holidayService.selectPageHolidayList(bo, pageQuery);
    }

    /**
     * 导出员工花名册Excel
     */
    @Log(title = "假期管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysHolidayBo bo) {
        List<SysHolidayVo> list = holidayService.selectHolidayList(bo);
        ExcelUtil.exportExcel(list, "假期管理", SysHolidayVo.class, response);
    }

}
