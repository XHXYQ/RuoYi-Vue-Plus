package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.SysPosition;
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
 * 系统职位管理视图对象 sys_position
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysPosition.class)
public class SysPositionVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键ID
     */
    @ExcelProperty(value = "自增主键ID")
    private Long id;

    /**
     * 职位编号（如POS-001）
     */
    @ExcelProperty(value = "职位编号", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "如=POS-001")
    private String code;

    /**
     * 职位名称
     */
    @ExcelProperty(value = "职位名称")
    private String name;

    /**
     * 职位描述
     */
    @ExcelProperty(value = "职位描述")
    private String description;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updatedAt;


}
