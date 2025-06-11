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
import org.dromara.system.domain.vo.SysChatSessionUserVo;
import org.dromara.system.domain.bo.SysChatSessionUserBo;
import org.dromara.system.service.ISysChatSessionUserService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 会话参与人：记录会话中参与的用户，支持多用户加入群聊
 *
 * @author Tim
 * @date 2025-06-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/chatSessionUser")
public class SysChatSessionUserController extends BaseController {

    private final ISysChatSessionUserService sysChatSessionUserService;

    /**
     * 查询会话参与人：记录会话中参与的用户，支持多用户加入群聊列表
     */
    @SaCheckPermission("system:chatSessionUser:list")
    @GetMapping("/list")
    public TableDataInfo<SysChatSessionUserVo> list(SysChatSessionUserBo bo, PageQuery pageQuery) {
        return sysChatSessionUserService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出会话参与人：记录会话中参与的用户，支持多用户加入群聊列表
     */
    @SaCheckPermission("system:chatSessionUser:export")
    @Log(title = "会话参与人：记录会话中参与的用户，支持多用户加入群聊", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysChatSessionUserBo bo, HttpServletResponse response) {
        List<SysChatSessionUserVo> list = sysChatSessionUserService.queryList(bo);
        ExcelUtil.exportExcel(list, "会话参与人：记录会话中参与的用户，支持多用户加入群聊", SysChatSessionUserVo.class, response);
    }

    /**
     * 获取会话参与人：记录会话中参与的用户，支持多用户加入群聊详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:chatSessionUser:query")
    @GetMapping("/{id}")
    public R<SysChatSessionUserVo> getInfo(@NotNull(message = "主键不能为空")
                                           @PathVariable Long id) {
        return R.ok(sysChatSessionUserService.queryById(id));
    }

    /**
     * 新增会话参与人：记录会话中参与的用户，支持多用户加入群聊
     */
    @SaCheckPermission("system:chatSessionUser:add")
    @Log(title = "会话参与人：记录会话中参与的用户，支持多用户加入群聊", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysChatSessionUserBo bo) {
        return toAjax(sysChatSessionUserService.insertByBo(bo));
    }

    /**
     * 修改会话参与人：记录会话中参与的用户，支持多用户加入群聊
     */
    @SaCheckPermission("system:chatSessionUser:edit")
    @Log(title = "会话参与人：记录会话中参与的用户，支持多用户加入群聊", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysChatSessionUserBo bo) {
        return toAjax(sysChatSessionUserService.updateByBo(bo));
    }

    /**
     * 删除会话参与人：记录会话中参与的用户，支持多用户加入群聊
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:chatSessionUser:remove")
    @Log(title = "会话参与人：记录会话中参与的用户，支持多用户加入群聊", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(sysChatSessionUserService.deleteWithValidByIds(List.of(ids), true));
    }
}
