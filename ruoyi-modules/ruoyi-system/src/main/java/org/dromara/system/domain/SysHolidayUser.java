package org.dromara.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_holiday_user")
public class SysHolidayUser {

    @TableId(value = "id")
    private Long id;

    private Long holidayId;

    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    private String balance;

    private String scopeType;  // 适用范围
    private String balanceType; // 余额规则
    private String holidayName; // 假期名称
    private Integer isUnlimited;

}
