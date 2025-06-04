package org.dromara.system.service;

import org.dromara.system.domain.vo.SysCompanyManagementVo;
import org.dromara.system.domain.bo.SysCompanyManagementBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 公司组织管理Service接口
 *
 * @author Lion Li
 * @date 2025-05-21
 */
public interface ISysCompanyManagementService {

    /**
     * 查询公司组织管理
     *
     * @param id 主键
     * @return 公司组织管理
     */
    SysCompanyManagementVo queryById(Long id);

    /**
     * 分页查询公司组织管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 公司组织管理分页列表
     */
    TableDataInfo<SysCompanyManagementVo> queryPageList(SysCompanyManagementBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的公司组织管理列表
     *
     * @param bo 查询条件
     * @return 公司组织管理列表
     */
    List<SysCompanyManagementVo> queryList(SysCompanyManagementBo bo);

    /**
     * 新增公司组织管理
     *
     * @param bo 公司组织管理
     * @return 是否新增成功
     */
    Boolean insertByBo(SysCompanyManagementBo bo);

    /**
     * 修改公司组织管理
     *
     * @param bo 公司组织管理
     * @return 是否修改成功
     */
    Boolean updateByBo(SysCompanyManagementBo bo);

    /**
     * 校验并批量删除公司组织管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
