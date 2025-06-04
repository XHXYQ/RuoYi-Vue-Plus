package org.dromara.workflow.service;

import org.dromara.workflow.domain.vo.TestRegularApplicationVo;
import org.dromara.workflow.domain.bo.TestRegularApplicationBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 转正申请Service接口
 *
 * @author Tim
 * @date 2025-05-24
 */
public interface ITestRegularApplicationService {

    /**
     * 查询转正申请
     *
     * @param id 主键
     * @return 转正申请
     */
    TestRegularApplicationVo queryById(Long id);

    /**
     * 分页查询转正申请列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 转正申请分页列表
     */
    TableDataInfo<TestRegularApplicationVo> queryPageList(TestRegularApplicationBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的转正申请列表
     *
     * @param bo 查询条件
     * @return 转正申请列表
     */
    List<TestRegularApplicationVo> queryList(TestRegularApplicationBo bo);

    /**
     * 新增转正申请
     *
     * @param bo 转正申请
     * @return 是否新增成功
     */
//    Boolean insertByBo(TestRegularApplicationBo bo);
//    Long insertByBo(TestRegularApplicationBo bo); // 修改返回值类型
    TestRegularApplicationVo insertByBo(TestRegularApplicationBo bo);

    /**
     * 修改转正申请
     *
     * @param bo 转正申请
     * @return 是否修改成功
     */
    Boolean updateByBo(TestRegularApplicationBo bo);

    /**
     * 校验并批量删除转正申请信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
