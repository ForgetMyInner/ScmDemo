package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanmait.scmdemo.entity.Approval;
import com.wanmait.scmdemo.entity.Supplier;
import com.wanmait.scmdemo.mapper.ApprovalMapper;
import com.wanmait.scmdemo.mapper.SupplierMapper;
import com.wanmait.scmdemo.service.ApprovalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 审批表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-12-27
 */
@Service
public class ApprovalServiceImpl extends ServiceImpl<ApprovalMapper, Approval> implements ApprovalService {
    @Resource
    private ApprovalMapper approvalMapper;
    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public List<Supplier> findWaitApprovalList() {
        LambdaQueryWrapper<Approval> approvalLambdaQueryWrapper = new LambdaQueryWrapper<>();
        approvalLambdaQueryWrapper.eq(Approval::getStatus, 0).eq(Approval::getSymbol, 1);
        List<Approval> approvals = approvalMapper.selectList(approvalLambdaQueryWrapper);

        // 使用Optional.ofNullable确保即使approvals为null也返回一个空列表
        List<Approval> nonNullApprovals = Optional.ofNullable(approvals).orElse(Collections.emptyList());

        List<Integer> aimIdsWithStatusZero = nonNullApprovals.stream()
                .filter(approval -> approval.getStatus() == false) // 过滤出status为0的记录
                .map(Approval::getAimId) // 提取aim_id
                .collect(Collectors.toList()); // 收集到列表中

        // 只有当aimIdsWithStatusZero不为空时，才调用selectBatchIds
        if (!aimIdsWithStatusZero.isEmpty()) {
            List<Supplier> suppliers = supplierMapper.selectBatchIds(aimIdsWithStatusZero);
            return suppliers;
        } else {
            // 返回一个空列表而不是null
            return Collections.emptyList();
        }
    }
}
