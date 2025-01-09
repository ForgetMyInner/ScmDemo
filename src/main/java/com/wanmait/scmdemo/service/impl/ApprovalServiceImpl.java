package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

    @Override
    public List<Supplier> findWaitSignList() {

        LambdaQueryWrapper<Approval> approvalLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 修改查询条件，添加 reason 为空的条件，并且 status 为 1，symbol 为 1
        //要status为1,symbol为1,并且reason为空的数据
        approvalLambdaQueryWrapper.eq(Approval::getStatus, 1)
                .eq(Approval::getSymbol, 1)
                .eq(Approval::getReason, "");
        List<Approval> approvals = approvalMapper.selectList(approvalLambdaQueryWrapper);

        // 使用 Optional.ofNullable 确保即使 approvals 为 null 也返回一个空列表
        List<Approval> nonNullApprovals = Optional.ofNullable(approvals).orElse(Collections.emptyList());

        // 提取 aimId
        List<Integer> aimIds = nonNullApprovals.stream()
                .map(Approval::getAimId)
                .collect(Collectors.toList());

        // 只有当 aimIds 不为空时，才调用 selectBatchIds
        if (!aimIds.isEmpty()) {
            List<Supplier> suppliers = supplierMapper.selectBatchIds(aimIds);
            return suppliers;
        } else {
            // 返回一个空列表而不是 null
            return Collections.emptyList();
        }
    }

    @Override
    public boolean updateApprovalByAimId(Integer aimId, Approval approval) {
        UpdateWrapper<Approval> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("aim_id", aimId);
        // 假设你要更新 symbol 和 status 字段，根据实际需求添加更多字段
        if (approval.getSymbol()!= null) {
            updateWrapper.set("symbol", approval.getSymbol());
        }
        if (approval.getStatus()!= null) {
            updateWrapper.set("status", approval.getStatus());
        }
        return approvalMapper.update(approval, updateWrapper) > 0;
    }
    //发现等待审批的供应商签约的数据们
    @Override
    public List<Supplier> findWaitApprovalSupplierSignList() {

        LambdaQueryWrapper<Approval> approvalLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 修改查询条件，添加 reason 为空的条件，并且 status 为 0，symbol 为 2
        //要status为0,symbol为2,并且reason为空的数据
        approvalLambdaQueryWrapper.eq(Approval::getStatus, 0)
                .eq(Approval::getSymbol, 2)
                .eq(Approval::getReason, "");
        List<Approval> approvals = approvalMapper.selectList(approvalLambdaQueryWrapper);

        // 使用 Optional.ofNullable 确保即使 approvals 为 null 也返回一个空列表
        List<Approval> nonNullApprovals = Optional.ofNullable(approvals).orElse(Collections.emptyList());

        // 提取 aimId
        List<Integer> aimIds = nonNullApprovals.stream()
                .map(Approval::getAimId)
                .collect(Collectors.toList());

        // 只有当 aimIds 不为空时，才调用 selectBatchIds
        if (!aimIds.isEmpty()) {
            List<Supplier> suppliers = supplierMapper.selectBatchIds(aimIds);
            return suppliers;
        } else {
            // 返回一个空列表而不是 null
            return Collections.emptyList();
        }
    }

}
