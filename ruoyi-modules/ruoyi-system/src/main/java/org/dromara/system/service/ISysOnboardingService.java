package org.dromara.system.service;

import org.dromara.system.domain.vo.SysOnboardingVo;
import org.dromara.system.domain.bo.SysOnboardingBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 员工入职申请Service接口
 *
 * @author Tim
 * @date 2025-06-03
 */
public interface ISysOnboardingService {

    /**
     * 查询员工入职申请
     *
     * @param onboardingId 主键
     * @return 员工入职申请
     */
    SysOnboardingVo queryById(Long onboardingId);

    /**
     * 分页查询员工入职申请列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 员工入职申请分页列表
     */
    TableDataInfo<SysOnboardingVo> queryPageList(SysOnboardingBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的员工入职申请列表
     *
     * @param bo 查询条件
     * @return 员工入职申请列表
     */
    List<SysOnboardingVo> queryList(SysOnboardingBo bo);

    /**
     * 新增员工入职申请
     *
     * @param bo 员工入职申请
     * @return 是否新增成功
     */
    Boolean insertByBo(SysOnboardingBo bo);

    /**
     * 修改员工入职申请
     *
     * @param bo 员工入职申请
     * @return 是否修改成功
     */
    Boolean updateByBo(SysOnboardingBo bo);

    /**
     * 校验并批量删除员工入职申请信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
