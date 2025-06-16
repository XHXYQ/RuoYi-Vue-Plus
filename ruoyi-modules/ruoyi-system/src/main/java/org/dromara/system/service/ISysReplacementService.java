package org.dromara.system.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.system.domain.bo.SysReplacementBo;
import org.dromara.system.domain.vo.SysReplacementVo;

import java.util.List;

public interface ISysReplacementService {

    /**
     * 查询请假
     */
    SysReplacementVo queryById(Long id);

    /**
     * 查询请假列表
     */
    TableDataInfo<SysReplacementVo> queryPageList(SysReplacementBo bo, PageQuery pageQuery);

    /**
     * 查询请假列表
     */
    List<SysReplacementVo> queryList(SysReplacementBo bo);

    /**
     * 新增请假
     */
    SysReplacementVo insertByBo(SysReplacementBo bo);

    /**
     * 修改请假
     */
    SysReplacementVo updateByBo(SysReplacementBo bo);

    /**
     * 校验并批量删除请假信息
     */
    Boolean deleteWithValidByIds(List<Long> ids);
}
