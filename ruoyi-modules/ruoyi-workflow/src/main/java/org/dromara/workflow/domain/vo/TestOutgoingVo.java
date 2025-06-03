package org.dromara.workflow.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.workflow.domain.TestOutgoing;
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
 * 外出申请视图对象 test_outgoing
 *
 * @author Tim
 * @date 2025-05-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TestOutgoing.class)
public class TestOutgoingVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 申请人
     */
    @ExcelProperty(value = "申请人")
    private String applicant;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 外出天数
     */
    @ExcelProperty(value = "外出天数")
    private Long leaveDay;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;


}
