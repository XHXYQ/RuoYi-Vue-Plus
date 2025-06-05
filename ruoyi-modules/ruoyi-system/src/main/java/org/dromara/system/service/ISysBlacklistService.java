package org.dromara.system.service;

import org.dromara.system.domain.vo.SysBlacklistVo;
import org.dromara.system.domain.bo.SysBlacklistBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 系统黑名单Service接口
 *
 * @author Tim
 * @date 2025-06-04
 */
public interface ISysBlacklistService {

    /**
     * 查询系统黑名单
     *
     * @param id 主键
     * @return 系统黑名单
     */
    SysBlacklistVo queryById(Long id);

    /**
     * 分页查询系统黑名单列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 系统黑名单分页列表
     */
    TableDataInfo<SysBlacklistVo> queryPageList(SysBlacklistBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的系统黑名单列表
     *
     * @param bo 查询条件
     * @return 系统黑名单列表
     */
    List<SysBlacklistVo> queryList(SysBlacklistBo bo);

    /**
     * 新增系统黑名单
     *
     * @param bo 系统黑名单
     * @return 是否新增成功
     */
    Boolean insertByBo(SysBlacklistBo bo);

    /**
     * 修改系统黑名单
     *
     * @param bo 系统黑名单
     * @return 是否修改成功
     */
    Boolean updateByBo(SysBlacklistBo bo);

    /**
     * 校验并批量删除系统黑名单信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
