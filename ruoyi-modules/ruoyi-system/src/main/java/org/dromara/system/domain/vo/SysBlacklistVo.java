package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.SysBlacklist;
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
 * 系统黑名单视图对象 sys_blacklist
 *
 * @author Tim
 * @date 2025-06-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SysBlacklist.class)
public class SysBlacklistVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String userName;

    /**
     * 证件号码
     */
    @ExcelProperty(value = "证件号码")
    private String idNumber;

    /**
     * 手机号码
     */
    @ExcelProperty(value = "手机号码")
    private String phonenumber;

    /**
     * 加黑原因
     */
    @ExcelProperty(value = "加黑原因")
    private String reason;

    /**
     * 加黑时间
     */
    @ExcelProperty(value = "加黑时间")
    private Date blacklistedAt;


}
