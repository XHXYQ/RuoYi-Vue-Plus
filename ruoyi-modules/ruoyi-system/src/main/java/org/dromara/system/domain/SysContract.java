package org.dromara.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import dm.jdbc.internal.desc.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_contract")
public class SysContract extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "user_id")
    private Long userId;


    private String name;


    private String jobnumber;


    private Long deptId;


    @TableField(exist = false)
    private String deptName;



    private String employeeType;


    private String contractnumber;


    private String contractType;


    private String statusType;


    private String deadlineType;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String statusxuType;

    private String company;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date signDate;

}

