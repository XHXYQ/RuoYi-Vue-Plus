package org.dromara.system.domain.vo;

import org.dromara.system.domain.SysCompanyManagement;
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
 * 公司组织管理视图对象 sys_company_management
 *
 * @author Lion Li
 * @date 2025-05-21
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysCompanyManagement.class)
public class SysCompanyManagementVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 唯一业务编号（如COMP-001）
     */
    @ExcelProperty(value = "唯一业务编号", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "如=COMP-001")
    private String code;

    /**
     * 部门/团队名称
     */
    @ExcelProperty(value = "部门/团队名称")
    private String name;

    /**
     * 成员人数（非负数）
     */
    @ExcelProperty(value = "成员人数", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "非=负数")
    private Long employeeCount;

    /**
     * 类型（如：部门/项目组/分公司）
     */
    @ExcelProperty(value = "类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "如=：部门/项目组/分公司")
    private String type;

    /**
     * 主管姓名
     */
    @ExcelProperty(value = "主管姓名")
    private String manager;


}
