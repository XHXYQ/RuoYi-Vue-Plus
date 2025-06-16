package org.dromara.system.domain;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * 假期配额规则
 */
@Data
public class HolidayQuotaRule {

    /**
     * 条件值（年数）
     * 例如：1表示≥1年，0表示<1年
     */
    @NotNull(message = "条件不能为空")
    @Min(value = 0, message = "条件最小值为0")
    private Integer condition;

    /**
     * 配额值（天数/小时数）
     */
    @NotNull(message = "配额值不能为空")
    @PositiveOrZero(message = "配额值必须大于等于0")
    private Integer value;

    /**
     * 条件类型
     * lt - 小于
     * gte - 大于等于
     */
    @NotBlank(message = "类型不能为空")
    @Pattern(regexp = "lt|gte", message = "类型只能是lt或gte")
    private String type;

    // 可以添加构造方法方便创建实例
    public HolidayQuotaRule() {
    }

    public HolidayQuotaRule(Integer condition, Integer value, String type) {
        this.condition = condition;
        this.value = value;
        this.type = type;
    }
}
