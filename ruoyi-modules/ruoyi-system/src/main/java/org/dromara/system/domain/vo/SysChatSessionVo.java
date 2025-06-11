package org.dromara.system.domain.vo;

import org.dromara.system.domain.SysChatSession;
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
 * 会话：用于记录会话（聊天房间），支持私聊或群聊视图对象 sys_chat_session
 *
 * @author Tim
 * @date 2025-06-09
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysChatSession.class)
public class SysChatSessionVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 会话类型（1=私聊，2=群聊）
     */
    @ExcelProperty(value = "会话类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1==私聊，2=群聊")
    private Long sessionType;

    /**
     * 创建人用户ID
     */
    @ExcelProperty(value = "创建人用户ID")
    private Long creatorId;


}
