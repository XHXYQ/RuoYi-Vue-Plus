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
import org.dromara.system.domain.vo.SysChatSessionVo;
import org.dromara.system.domain.bo.SysChatSessionBo;
import org.dromara.system.service.ISysChatSessionService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 会话：用于记录会话（聊天房间），支持私聊或群聊
 *
 * @author Tim
 * @date 2025-06-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/chatSession")
public class SysChatSessionController extends BaseController {

    private final ISysChatSessionService sysChatSessionService;

    /**
     * 查询会话：用于记录会话（聊天房间），支持私聊或群聊列表
     */
    @SaCheckPermission("system:chatSession:list")
    @GetMapping("/list")
    public TableDataInfo<SysChatSessionVo> list(SysChatSessionBo bo, PageQuery pageQuery) {
        return sysChatSessionService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出会话：用于记录会话（聊天房间），支持私聊或群聊列表
     */
    @SaCheckPermission("system:chatSession:export")
    @Log(title = "会话：用于记录会话（聊天房间），支持私聊或群聊", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysChatSessionBo bo, HttpServletResponse response) {
        List<SysChatSessionVo> list = sysChatSessionService.queryList(bo);
        ExcelUtil.exportExcel(list, "会话：用于记录会话（聊天房间），支持私聊或群聊", SysChatSessionVo.class, response);
    }

    /**
     * 获取会话：用于记录会话（聊天房间），支持私聊或群聊详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:chatSession:query")
    @GetMapping("/{id}")
    public R<SysChatSessionVo> getInfo(@NotNull(message = "主键不能为空")
                                       @PathVariable Long id) {
        return R.ok(sysChatSessionService.queryById(id));
    }

    /**
     * 新增会话：用于记录会话（聊天房间），支持私聊或群聊
     */
    @SaCheckPermission("system:chatSession:add")
    @Log(title = "会话：用于记录会话（聊天房间），支持私聊或群聊", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysChatSessionBo bo) {
        return toAjax(sysChatSessionService.insertByBo(bo));
    }

    /**
     * 修改会话：用于记录会话（聊天房间），支持私聊或群聊
     */
    @SaCheckPermission("system:chatSession:edit")
    @Log(title = "会话：用于记录会话（聊天房间），支持私聊或群聊", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysChatSessionBo bo) {
        return toAjax(sysChatSessionService.updateByBo(bo));
    }

    /**
     * 删除会话：用于记录会话（聊天房间），支持私聊或群聊
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:chatSession:remove")
    @Log(title = "会话：用于记录会话（聊天房间），支持私聊或群聊", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(sysChatSessionService.deleteWithValidByIds(List.of(ids), true));
    }
}
