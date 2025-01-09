package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.Approval;
import com.wanmait.scmdemo.entity.Warehouse;
import com.wanmait.scmdemo.mapper.ApprovalMapper;
import com.wanmait.scmdemo.service.ApprovalService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 审批表 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-12-27
 */
@RestController
@RequestMapping("manage/approval")
public class ApprovalController {
    @Resource
    private ApprovalService approvalService;
    @GetMapping("/list")
    public AjaxResult list(){
        if (approvalService.findWaitApprovalList()!=null){
            return AjaxResult.success("查询成功").put("name","mazihao").put("waitApprovalenterSupplier",approvalService.findWaitApprovalList());
        }
        return null;
    }

    @GetMapping("/waitSignList")
    public AjaxResult waitSignList(){
        if (approvalService.findWaitSignList()!=null){
            return AjaxResult.success("查询成功").put("name","Tong").put("waitApprovalenterSignSupplier",approvalService.findWaitSignList());
        }
        return null;
    }
    @PutMapping("/updateApprovalByAimId")
    public AjaxResult updateApprovalByAimId(@RequestBody Approval approval) {
        if (approval.getAimId() == null) {
            return AjaxResult.error("aimId 不能为空");
        }
        approval.setSymbol(2);
        approval.setStatus(false);
        boolean success = approvalService.updateApprovalByAimId(approval.getAimId(), approval);
        if (success) {
            return AjaxResult.success("更新成功");
        } else {
            return AjaxResult.error("更新失败");
        }
    }
    @PutMapping("/submitApproval")
    public AjaxResult submitApprovalForm(@RequestBody Approval approval) {
        if (approval.getAimId() == null) {
            return AjaxResult.error("aimId 不能为空");
        }
        approval.setSymbol(2);
        approval.setStatus(true);
        System.out.println("11111111111111");
        System.out.println(approval);
        System.out.println("2222222222222");
        boolean success = approvalService.updateApprovalByAimId(approval.getAimId(), approval);
        if (success) {
            return AjaxResult.success("提交审批成功");
        } else {
            return AjaxResult.error("提交审批失败");
        }
    }

    @GetMapping("/waitApprovalSupplierSignList")
    public AjaxResult waitApprovalSupplierSignList(){
        if (approvalService.findWaitApprovalSupplierSignList()!=null){
            return AjaxResult.success("查询成功").put("name","Tong").put("waitApprovalSupplierSignList",approvalService.findWaitApprovalSupplierSignList());
        }
        return null;
    }
}



