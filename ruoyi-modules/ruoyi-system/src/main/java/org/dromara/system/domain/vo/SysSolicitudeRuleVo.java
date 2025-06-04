package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.SysSolicitudeRule;
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
 * 员工关怀规则配置视图对象 sys_solicitude_rule
 *
 * @author Tim
 * @date 2025-06-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysSolicitudeRule.class)
public class SysSolicitudeRuleVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 关怀类型ID（关联 sys_solicitude_category）
     */
    @ExcelProperty(value = "关怀类型ID", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "关=联,s=ys_solicitude_category")
    private Long categoryId;

    /**
     * 系统祝福是否启用（1=启用，0=关闭）
     */
    @ExcelProperty(value = "系统祝福是否启用", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1==启用，0=关闭")
    private Long enableSystemRemind;

    /**
     * 全员祝福是否启用（1=启用，0=关闭）
     */
    @ExcelProperty(value = "全员祝福是否启用", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1==启用，0=关闭")
    private Long enableAllRemind;

    /**
     * 提醒给同事（1=启用，0=关闭）
     */
    @ExcelProperty(value = "提醒给同事", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1==启用，0=关闭")
    private Long enableColleagueRemind;

    /**
     * 提前几天提醒（默认0为当天）
     */
    @ExcelProperty(value = "提前几天提醒", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "默=认0为当天")
    private Long remindDaysBefore;

    /**
     * 提醒时间点
     */
    @ExcelProperty(value = "提醒时间点")
    private Date remindTime;

    /**
     * 祝福模板内容
     */
    @ExcelProperty(value = "祝福模板内容")
    private String messageTemplate;

    /**
     * 创建人
     */
    @ExcelProperty(value = "创建人")
    private Long createdBy;

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

    /**
     * 删除时间（软删）
     */
    @ExcelProperty(value = "删除时间", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "软=删")
    private Date deletedAt;


}
