package org.dromara.system.domain.vo;

import org.dromara.system.domain.SysOrgType;
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
 * 组织类型视图对象 sys_org_type
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysOrgType.class)
public class SysOrgTypeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @ExcelProperty(value = "主键 ID")
    private Long id;

    /**
     * 编号
     */
    @ExcelProperty(value = "编号")
    private String code;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String name;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;


}
