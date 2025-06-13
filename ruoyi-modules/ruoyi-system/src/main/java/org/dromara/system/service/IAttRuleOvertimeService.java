package org.dromara.system.service;

import org.dromara.system.domain.vo.AttRuleOvertimeVo;
import org.dromara.system.domain.bo.AttRuleOvertimeBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 加班规则Service接口
 *
 * @author Lion Li
 * @date 2025-06-13
 */
public interface IAttRuleOvertimeService {

    /**
     * 查询加班规则
     *
     * @param id 主键
     * @return 加班规则
     */
    AttRuleOvertimeVo queryById(Long id);

    /**
     * 分页查询加班规则列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 加班规则分页列表
     */
    TableDataInfo<AttRuleOvertimeVo> queryPageList(AttRuleOvertimeBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的加班规则列表
     *
     * @param bo 查询条件
     * @return 加班规则列表
     */
    List<AttRuleOvertimeVo> queryList(AttRuleOvertimeBo bo);

    /**
     * 新增加班规则
     *
     * @param bo 加班规则
     * @return 是否新增成功
     */
    Boolean insertByBo(AttRuleOvertimeBo bo);

    /**
     * 修改加班规则
     *
     * @param bo 加班规则
     * @return 是否修改成功
     */
    Boolean updateByBo(AttRuleOvertimeBo bo);

    /**
     * 校验并批量删除加班规则信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
