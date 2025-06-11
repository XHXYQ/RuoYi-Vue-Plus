package org.dromara.system.controller.system;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.system.domain.dto.SendGroupMessageRequest;
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
import org.dromara.system.domain.vo.SysChatMessageVo;
import org.dromara.system.domain.bo.SysChatMessageBo;
import org.dromara.system.service.ISysChatMessageService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 消息：用于记录聊天内容
 *
 * @author Tim
 * @date 2025-06-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/chatMessage")
public class SysChatMessageController extends BaseController {

    private final ISysChatMessageService sysChatMessageService;

    /**
     * 查询消息：用于记录聊天内容列表
     */
    @SaCheckPermission("system:chatMessage:list")
    @GetMapping("/list")
    public TableDataInfo<SysChatMessageVo> list(SysChatMessageBo bo, PageQuery pageQuery) {
        return sysChatMessageService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出消息：用于记录聊天内容列表
     */
    @SaCheckPermission("system:chatMessage:export")
    @Log(title = "消息：用于记录聊天内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysChatMessageBo bo, HttpServletResponse response) {
        List<SysChatMessageVo> list = sysChatMessageService.queryList(bo);
        ExcelUtil.exportExcel(list, "消息：用于记录聊天内容", SysChatMessageVo.class, response);
    }

    /**
     * 获取消息：用于记录聊天内容详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:chatMessage:query")
    @GetMapping("/{id}")
    public R<SysChatMessageVo> getInfo(@NotNull(message = "主键不能为空")
                                       @PathVariable Long id) {
        return R.ok(sysChatMessageService.queryById(id));
    }

    /**
     * 新增消息：用于记录聊天内容
     */
    @SaCheckPermission("system:chatMessage:add")
    @Log(title = "消息：用于记录聊天内容", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysChatMessageBo bo) {
        return toAjax(sysChatMessageService.insertByBo(bo));
    }

    /**
     * 修改消息：用于记录聊天内容
     */
    @SaCheckPermission("system:chatMessage:edit")
    @Log(title = "消息：用于记录聊天内容", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysChatMessageBo bo) {
        return toAjax(sysChatMessageService.updateByBo(bo));
    }

    /**
     * 删除消息：用于记录聊天内容
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:chatMessage:remove")
    @Log(title = "消息：用于记录聊天内容", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(sysChatMessageService.deleteWithValidByIds(List.of(ids), true));
    }


    /**
     * 群聊消息发送（带推送）
     */
    @Log(title = "群聊消息发送", businessType = BusinessType.OTHER)
    @PostMapping("/send/group")
    public R<Boolean> sendGroupMessage(@Validated @RequestBody SendGroupMessageRequest request) {
        return R.ok(sysChatMessageService.sendGroupMessage(request.getSessionId(), request.getMessage()));
    }

}
