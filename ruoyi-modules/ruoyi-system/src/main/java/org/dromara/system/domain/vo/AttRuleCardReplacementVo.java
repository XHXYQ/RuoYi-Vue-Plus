package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.AttRuleCardReplacement;
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
 * 补卡规则视图对象 att_rule_card_replacement
 *
 * @author Skye
 * @date 2025-06-13
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AttRuleCardReplacement.class)
public class AttRuleCardReplacementVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 补卡规则名称
     */
    @ExcelProperty(value = "补卡规则名称")
    private String name;

    /**
     * 应用范围（考勤组id）
     */
    @ExcelProperty(value = "应用范围", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "考=勤组id")
    private String groupsId;

    /**
     * 负责人id数组
     */
    @ExcelProperty(value = "负责人id数组")
    private String director;

    /**
     * 是否允许补卡 1允许 2不允许
     */
    @ExcelProperty(value = "是否允许补卡 1允许 2不允许")
    private Long allow;

    /**
     * 每月可以交数量
     */
    @ExcelProperty(value = "每月可以交数量")
    private Long num;

    /**
     * 每月几号重算
     */
    @ExcelProperty(value = "每月几号重算")
    private String date;

    /**
     * 可申请过去多少天的补卡
     */
    @ExcelProperty(value = "可申请过去多少天的补卡")
    private Long pastDay;

    /**
     * 1工作日  2自然日
     */
    @ExcelProperty(value = "1工作日  2自然日")
    private Long dayType;

    /**
     * 补卡类型 1缺卡 2迟到 3早退 4正常
     */
    @ExcelProperty(value = "补卡类型 1缺卡 2迟到 3早退 4正常")
    private String type;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Date deletedAt;

    /**
     * 是否默认 1是 0否
     */
    @ExcelProperty(value = "是否默认 1是 0否")
    private Long isDefault;


}
