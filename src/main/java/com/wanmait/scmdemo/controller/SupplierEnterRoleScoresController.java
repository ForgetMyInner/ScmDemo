package com.wanmait.scmdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanmait.scmdemo.entity.Approval;
import com.wanmait.scmdemo.entity.Supplier;
import com.wanmait.scmdemo.entity.SupplierEnterRoleScores;
import com.wanmait.scmdemo.service.ApprovalService;
import com.wanmait.scmdemo.service.SupplierEnterRoleScoresService;
import com.wanmait.scmdemo.service.SupplierService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商准入得分 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-12-25
 */
@RestController
@RequestMapping("/manage/supplierEnterRoleScores")
public class SupplierEnterRoleScoresController {
    @Resource
    private SupplierEnterRoleScoresService supplierEnterRoleScoresService;
    @Resource
    private SupplierService supplierService;
    @Resource
    private ApprovalService approvalService;

    @PostMapping("/addScores")
    public AjaxResult addScores(@RequestBody Map<String, Object>  requestBody){
        Integer supplierId = (Integer) requestBody.get("supplierId");
        String clauseName = (String) requestBody.get("clauseName");
        Integer maxScore = (Integer) requestBody.get("maxScore");
        Integer ruleId = (Integer) requestBody.get("ruleId");
        String supplierLevel =(String) requestBody.get("supplierRating");
        List<Map<String, Object>> clauseItems = (List<Map<String, Object>>) requestBody.get("clauseItems");
        supplierEnterRoleScoresService.saveScores(supplierId,clauseName,maxScore,clauseItems,supplierLevel,ruleId);
        Supplier supplier = supplierService.getById(supplierId);
        if (supplier!=null){
            supplier.setSupplierLevel(supplierLevel);
            supplierService.updateById(supplier);
        }
        return AjaxResult.success("添加成功");
    }
    @GetMapping("{supplierId:\\d+}")
    public AjaxResult getSupplierScores(@PathVariable Integer supplierId){
        if (supplierId==null){
            return AjaxResult.error();
        }
        System.out.println(supplierEnterRoleScoresService.getBySupplierId(supplierId));
        return  AjaxResult.success("").put("scores",supplierEnterRoleScoresService.getBySupplierId(supplierId));
    }
    @PostMapping("submitApproval")
    public AjaxResult submitApproval(@RequestBody Map<String, Object> approvalData){

        String agreement = (String) approvalData.get("agreement");
        String frontendDate=(String) approvalData.get("approvalTime");
        String isoDateString = frontendDate;

        // 解析ISO 8601格式的日期字符串
        OffsetDateTime odt = OffsetDateTime.parse(isoDateString);

        // 将OffsetDateTime转换为Instant
        java.time.Instant instant = odt.toInstant();

        // 将Instant转换为java.util.Date
        Date date = Date.from(instant);
        String approver = (String) approvalData.get("approver");
        String reason = (String) approvalData.get("reason");
        Integer supplierId = (Integer) approvalData.get("supplierId");
        QueryWrapper<Approval> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("aim_id", supplierId).eq("symbol", 1); // 假设字段名为 supplier_id 和 symbol

        // 执行查询
        Approval approval = approvalService.getOne(queryWrapper);
        approval.setTime(date);
        approval.setReason(reason);
        if (agreement.equals("同意")){
            approval.setStatus(true);
        }else {
            approval.setStatus(true);
            approval.setReason(reason);
        }
        approvalService.updateById(approval);

        System.out.println(approval);

        return AjaxResult.success("success").put("success","success");
    }


}
