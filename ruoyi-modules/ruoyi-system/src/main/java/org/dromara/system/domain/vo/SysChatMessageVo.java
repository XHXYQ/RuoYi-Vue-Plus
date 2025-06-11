package org.dromara.system.domain.vo;

import org.dromara.system.domain.SysChatMessage;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;



/**
 * 消息：用于记录聊天内容视图对象 sys_chat_message
 *
 * @author Tim
 * @date 2025-06-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysChatMessage.class)
public class SysChatMessageVo implements Serializable {

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
     * 发送者ID
     */
    @ExcelProperty(value = "发送者ID")
    private Long senderId;

    /**
     * 消息内容（支持文本、图片、文件等）
     */
    @ExcelProperty(value = "消息内容", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "支=持文本、图片、文件等")
    private String content;

    /**
     * 消息类型（1=文本，2=图片，3=文件等）
     */
    @ExcelProperty(value = "消息类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1==文本，2=图片，3=文件等")
    private Long messageType;

    private LocalDateTime sendTime;



}
