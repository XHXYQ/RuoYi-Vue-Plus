package org.dromara.workflow.service;

import org.dromara.workflow.domain.TestOutgoing;
import org.dromara.workflow.domain.bo.TestRegularApplicationBo;
import org.dromara.workflow.domain.vo.TestOutgoingVo;
import org.dromara.workflow.domain.bo.TestOutgoingBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.workflow.domain.vo.TestRegularApplicationVo;

import java.util.Collection;
import java.util.List;

/**
 * 外出申请Service接口
 *
 * @author Tim
 * @date 2025-05-28
 */
public interface ITestOutgoingService {

    /**
     * 查询外出申请
     *
     * @param id 主键
     * @return 外出申请
     */
    TestOutgoingVo queryById(Long id);

    /**
     * 分页查询外出申请列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 外出申请分页列表
     */
    TableDataInfo<TestOutgoingVo> queryPageList(TestOutgoingBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的外出申请列表
     *
     * @param bo 查询条件
     * @return 外出申请列表
     */
    List<TestOutgoingVo> queryList(TestOutgoingBo bo);

    /**
     * 新增外出申请
     *
     * @param bo 外出申请
     * @return 是否新增成功
     */
//    Boolean insertByBo(TestOutgoingBo bo);
    TestOutgoingVo insertByBo(TestOutgoingBo bo);

    /**
     * 修改外出申请
     *
     * @param bo 外出申请
     * @return 是否修改成功
     */
    Boolean updateByBo(TestOutgoingBo bo);

    /**
     * 校验并批量删除外出申请信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
