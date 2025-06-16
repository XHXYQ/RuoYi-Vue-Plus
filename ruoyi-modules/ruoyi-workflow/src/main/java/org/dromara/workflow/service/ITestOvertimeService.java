package org.dromara.workflow.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.workflow.domain.bo.TestOvertimeBo;
import org.dromara.workflow.domain.vo.TestOvertimeVo;

import java.util.List;

/**
 * 请假Service接口
 *
 * @author may
 * @date 2023-07-21
 */
public interface ITestOvertimeService {

    /**
     * 查询请假
     */
    TestOvertimeVo queryById(Long id);

    /**
     * 查询请假列表
     */
    TableDataInfo<TestOvertimeVo> queryPageList(TestOvertimeBo bo, PageQuery pageQuery);

    /**
     * 查询请假列表
     */
    List<TestOvertimeVo> queryList(TestOvertimeBo bo);

    /**
     * 新增请假
     */
    TestOvertimeVo insertByBo(TestOvertimeBo bo);

    /**
     * 修改请假
     */
    TestOvertimeVo updateByBo(TestOvertimeBo bo);

    /**
     * 校验并批量删除请假信息
     */
    Boolean deleteWithValidByIds(List<Long> ids);
}
