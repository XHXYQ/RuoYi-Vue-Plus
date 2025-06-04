package org.dromara.system.service;

import org.dromara.system.domain.vo.SysOrgTypeVo;
import org.dromara.system.domain.bo.SysOrgTypeBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 组织类型Service接口
 *
 * @author Lion Li
 * @date 2025-05-21
 */
public interface ISysOrgTypeService {

    /**
     * 查询组织类型
     *
     * @param id 主键
     * @return 组织类型
     */
    SysOrgTypeVo queryById(Long id);

    /**
     * 分页查询组织类型列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 组织类型分页列表
     */
    TableDataInfo<SysOrgTypeVo> queryPageList(SysOrgTypeBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的组织类型列表
     *
     * @param bo 查询条件
     * @return 组织类型列表
     */
    List<SysOrgTypeVo> queryList(SysOrgTypeBo bo);

    /**
     * 新增组织类型
     *
     * @param bo 组织类型
     * @return 是否新增成功
     */
    Boolean insertByBo(SysOrgTypeBo bo);

    /**
     * 修改组织类型
     *
     * @param bo 组织类型
     * @return 是否修改成功
     */
    Boolean updateByBo(SysOrgTypeBo bo);

    /**
     * 校验并批量删除组织类型信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
