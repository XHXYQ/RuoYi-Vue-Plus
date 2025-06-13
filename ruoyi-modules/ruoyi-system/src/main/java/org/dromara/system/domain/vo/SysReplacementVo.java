package org.dromara.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.system.domain.SysReplacement;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysReplacement.class)
public class SysReplacementVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 补卡时间
     */
    @ExcelProperty(value = "补卡时间")
    private Date replacementDate;


    /**
     * 备注
     */
    @ExcelProperty(value = "补卡原因")
    private String remark;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;
}
