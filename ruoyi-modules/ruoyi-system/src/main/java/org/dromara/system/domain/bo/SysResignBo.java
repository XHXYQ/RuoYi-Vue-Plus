package org.dromara.system.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.system.domain.SysResign;

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysResign.class, reverseConvertGenerate = false)
public class SysResignBo extends BaseEntity {
}
