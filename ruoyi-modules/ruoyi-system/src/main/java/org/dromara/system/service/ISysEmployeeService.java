package org.dromara.system.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.system.domain.bo.SysEmployeeBo;
import org.dromara.system.domain.vo.EmployeeStatsVo;
import org.dromara.system.domain.vo.SysEmployeeVo;

import java.util.List;

/**
 * 员工管理 服务层
 */
public interface ISysEmployeeService {

    /**
     * 添加员工
     *
     * @param bo 员工业务对象
     * @return 插入结果（1=成功，0=失败）
     */
    int insertEmployee(SysEmployeeBo bo);

    /**
     * 修改员工信息
     *
     * @param bo 员工业务对象
     * @return 更新结果（1=成功，0=失败）
     */
    int updateEmployee(SysEmployeeBo bo);

    /**
     * 批量删除员工
     *
     * @param ids 需要删除的员工ID数组
     * @return 删除结果（成功删除的数量）
     */
    int deleteEmployeeByIds(Long[] ids);

    /**
     * 根据ID查询员工详细信息
     *
     * @param id 员工ID
     * @return 员工视图对象
     */
    SysEmployeeVo selectEmployeeById(Long id);

    /**
     * 分页查询员工列表
     *
     * @param bo 查询条件
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    TableDataInfo<SysEmployeeVo> selectPageEmployeeList(SysEmployeeBo bo, PageQuery pageQuery);

    /**
     * 查询员工列表（不分页，用于导出）
     *
     * @param bo 查询条件
     * @return 员工列表
     */
    List<SysEmployeeVo> selectEmployeeList(SysEmployeeBo bo);

    /**
     * 获取在职员工数量
     *
     * @return 在职员工数量
     */
    long getActiveEmployeeCount();

    /**
     * 新增员工并同步到用户表
     */
    boolean insertEmployeeAndUser(SysEmployeeBo bo);

    EmployeeStatsVo getEmployeeStats();
}
