package org.dromara.system.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.system.domain.SysHoliday;
import org.dromara.system.domain.bo.SysContractBo;
import org.dromara.system.domain.bo.SysHolidayBo;
import org.dromara.system.domain.vo.SysContractVo;
import org.dromara.system.domain.vo.SysHolidayVo;

import java.util.Collection;
import java.util.List;

/**
 * 假期规则服务接口
 */
public interface ISysHolidayService {

    /**
     * 添加员工
     *
     * @param bo 员工业务对象
     * @return 插入结果（1=成功，0=失败）
     */
    int insertHoliday(SysHolidayBo bo);

    /**
     * 修改员工信息
     *
     * @param bo 员工业务对象
     * @return 更新结果（1=成功，0=失败）
     */
    int updateHoliday(SysHolidayBo bo);

    /**
     * 批量删除员工
     *
     * @param ids 需要删除的员工ID数组
     * @return 删除结果（成功删除的数量）
     */
    int deleteHolidayByIds(Long[] ids);

    /**
     * 根据ID查询员工详细信息
     *
     * @param id 员工ID
     * @return 员工视图对象
     */
    SysHolidayVo selectHolidayById(Long id);

    /**
     * 分页查询员工列表
     *
     * @param bo 查询条件
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    TableDataInfo<SysHolidayVo> selectPageHolidayList(SysHolidayBo bo, PageQuery pageQuery);

    /**
     * 查询员工列表（不分页，用于导出）
     *
     * @param bo 查询条件
     * @return 员工列表
     */
    List<SysHolidayVo> selectHolidayList(SysHolidayBo bo);

    boolean insertHolidayAndUser(SysHolidayBo bo);



}
