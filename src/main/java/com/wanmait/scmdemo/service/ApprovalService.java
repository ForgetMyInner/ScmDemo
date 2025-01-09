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
}
