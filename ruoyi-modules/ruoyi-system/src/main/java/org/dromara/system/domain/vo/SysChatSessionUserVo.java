package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.SysChatSessionUser;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 会话参与人：记录会话中参与的用户，支持多用户加入群聊视图对象 sys_chat_session_user
 *
 * @author Tim
 * @date 2025-06-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysChatSessionUser.class)
public class SysChatSessionUserVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 会话ID
     */
    @ExcelProperty(value = "会话ID")
    private Long sessionId;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 加入时间
     */
    @ExcelProperty(value = "加入时间")
    private Date joinTime;

    /**
     * 是否被禁言（0否 1是）
     */
    @ExcelProperty(value = "是否被禁言", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=否,1=是")
    private Long isMuted;


}
