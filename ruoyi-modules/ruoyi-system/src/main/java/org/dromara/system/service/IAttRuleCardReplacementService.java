package org.dromara.system.service;

import org.dromara.system.domain.vo.AttRuleCardReplacementVo;
import org.dromara.system.domain.bo.AttRuleCardReplacementBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 补卡规则Service接口
 *
 * @author Skye
 * @date 2025-06-13
 */
public interface IAttRuleCardReplacementService {

    /**
     * 查询补卡规则
     *
     * @param id 主键
     * @return 补卡规则
     */
    AttRuleCardReplacementVo queryById(Long id);

    /**
     * 分页查询补卡规则列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 补卡规则分页列表
     */
    TableDataInfo<AttRuleCardReplacementVo> queryPageList(AttRuleCardReplacementBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的补卡规则列表
     *
     * @param bo 查询条件
     * @return 补卡规则列表
     */
    List<AttRuleCardReplacementVo> queryList(AttRuleCardReplacementBo bo);

    /**
     * 新增补卡规则
     *
     * @param bo 补卡规则
     * @return 是否新增成功
     */
    Boolean insertByBo(AttRuleCardReplacementBo bo);

    /**
     * 修改补卡规则
     *
     * @param bo 补卡规则
     * @return 是否修改成功
     */
    Boolean updateByBo(AttRuleCardReplacementBo bo);

    /**
     * 校验并批量删除补卡规则信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
