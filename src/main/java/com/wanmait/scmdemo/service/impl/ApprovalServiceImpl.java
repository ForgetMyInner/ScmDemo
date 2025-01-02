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
import java.util.List;
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
        approvalLambdaQueryWrapper.eq(Approval::getStatus,0).eq(Approval::getSymbol,1);
        List<Approval> approvals = approvalMapper.selectList(approvalLambdaQueryWrapper);
        List<Integer> aimIdsWithStatusZero = approvals.stream()
                .filter(approval -> approval.getStatus() == false) // 过滤出status为0的记录
                .map(Approval::getAimId) // 提取aim_id
                .collect(Collectors.toList()); // 收集到列表中
        List<Supplier> suppliers = supplierMapper.selectBatchIds(aimIdsWithStatusZero);


        return suppliers;
    }
}
