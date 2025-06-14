package org.dromara.system.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.system.domain.SysTransfer;
import org.dromara.system.domain.bo.SysTransferBo;
import org.dromara.system.domain.vo.SysTransferVo;


import java.util.List;

public interface ISysTransferService {

    /**
     * 查询请假
     */
    SysTransferVo queryById(Long id);

    /**
     * 查询请假列表
     */
    TableDataInfo<SysTransferVo> queryPageList(SysTransferBo bo, PageQuery pageQuery);

    /**
     * 查询请假列表
     */
    List<SysTransferVo> queryList(SysTransferBo bo);

    /**
     * 新增请假
     */
    SysTransferVo insertByBo(SysTransferBo bo);

    /**
     * 修改请假
     */
    SysTransferVo updateByBo(SysTransferBo bo);

    /**
     * 校验并批量删除请假信息
     */
    Boolean deleteWithValidByIds(List<Long> ids);

}
