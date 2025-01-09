package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.Approval;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanmait.scmdemo.entity.Supplier;

import java.util.List;

/**
 * <p>
 * 审批表 服务类
 * </p>
 *
 * @author wanmait
 * @since 2024-12-27
 */
public interface ApprovalService extends IService<Approval> {
    List<Supplier> findWaitApprovalList();
    List<Supplier> findWaitSignList();
    //根据aimId来修改审批表
    boolean updateApprovalByAimId(Integer aimId, Approval approval);

    //发现等待审批的供应商签约的数据们
    List<Supplier> findWaitApprovalSupplierSignList();
}
