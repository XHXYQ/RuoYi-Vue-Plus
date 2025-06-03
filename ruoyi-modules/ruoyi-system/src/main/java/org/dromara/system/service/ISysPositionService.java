package org.dromara.system.service;

import org.dromara.system.domain.vo.SysPositionVo;
import org.dromara.system.domain.bo.SysPositionBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 系统职位管理Service接口
 *
 * @author Lion Li
 * @date 2025-05-21
 */
public interface ISysPositionService {

    /**
     * 查询系统职位管理
     *
     * @param id 主键
     * @return 系统职位管理
     */
    SysPositionVo queryById(Long id);

    /**
     * 分页查询系统职位管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 系统职位管理分页列表
     */
    TableDataInfo<SysPositionVo> queryPageList(SysPositionBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的系统职位管理列表
     *
     * @param bo 查询条件
     * @return 系统职位管理列表
     */
    List<SysPositionVo> queryList(SysPositionBo bo);

    /**
     * 新增系统职位管理
     *
     * @param bo 系统职位管理
     * @return 是否新增成功
     */
    Boolean insertByBo(SysPositionBo bo);

    /**
     * 修改系统职位管理
     *
     * @param bo 系统职位管理
     * @return 是否修改成功
     */
    Boolean updateByBo(SysPositionBo bo);

    /**
     * 校验并批量删除系统职位管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
