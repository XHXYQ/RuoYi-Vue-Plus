package org.dromara.system.service;

import org.dromara.system.domain.vo.SysSolicitudeRuleVo;
import org.dromara.system.domain.bo.SysSolicitudeRuleBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 员工关怀规则配置Service接口
 *
 * @author Tim
 * @date 2025-06-04
 */
public interface ISysSolicitudeRuleService {

    /**
     * 查询员工关怀规则配置
     *
     * @param id 主键
     * @return 员工关怀规则配置
     */
    SysSolicitudeRuleVo queryById(Long id);

    /**
     * 分页查询员工关怀规则配置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 员工关怀规则配置分页列表
     */
    TableDataInfo<SysSolicitudeRuleVo> queryPageList(SysSolicitudeRuleBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的员工关怀规则配置列表
     *
     * @param bo 查询条件
     * @return 员工关怀规则配置列表
     */
    List<SysSolicitudeRuleVo> queryList(SysSolicitudeRuleBo bo);

    /**
     * 新增员工关怀规则配置
     *
     * @param bo 员工关怀规则配置
     * @return 是否新增成功
     */
    Boolean insertByBo(SysSolicitudeRuleBo bo);

    /**
     * 修改员工关怀规则配置
     *
     * @param bo 员工关怀规则配置
     * @return 是否修改成功
     */
    Boolean updateByBo(SysSolicitudeRuleBo bo);

    /**
     * 校验并批量删除员工关怀规则配置信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
