package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.Approval;
import com.wanmait.scmdemo.mapper.ApprovalMapper;
import com.wanmait.scmdemo.service.ApprovalService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
        return AjaxResult.success("查询成功").put("name","mazihao").put("waitApprovalenterSupplier",approvalService.findWaitApprovalList());
    }



}
