package org.dromara.workflow.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.workflow.domain.bo.TestBusinessBo;
import org.dromara.workflow.domain.vo.TestBusinessVo;

import java.util.List;

/**
 * 请假Service接口
 *
 * @author may
 * @date 2023-07-21
 */
public interface ITestBusinessService {

    /**
     * 查询请假
     */
    TestBusinessVo queryById(Long id);

    /**
     * 查询请假列表
     */
    TableDataInfo<TestBusinessVo> queryPageList(TestBusinessBo bo, PageQuery pageQuery);

    /**
     * 查询请假列表
     */
    List<TestBusinessVo> queryList(TestBusinessBo bo);

    /**
     * 新增请假
     */
    TestBusinessVo insertByBo(TestBusinessBo bo);

    /**
     * 修改请假
     */
    TestBusinessVo updateByBo(TestBusinessBo bo);

    /**
     * 校验并批量删除请假信息
     */
    Boolean deleteWithValidByIds(List<Long> ids);
}

