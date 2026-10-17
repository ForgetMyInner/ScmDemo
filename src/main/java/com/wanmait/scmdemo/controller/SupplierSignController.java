package com.wanmait.scmdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wanmait.scmdemo.entity.Approval;
import com.wanmait.scmdemo.entity.Supplier;
import com.wanmait.scmdemo.entity.SupplierSign;
import com.wanmait.scmdemo.entity.Warehouse;
import com.wanmait.scmdemo.service.ApprovalService;
import com.wanmait.scmdemo.service.SupplierService;
import com.wanmait.scmdemo.service.SupplierSignService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.*;

/**
 * <p>
 * 供应商签约 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/supplierSign")
public class SupplierSignController {

    @Resource
    private SupplierSignService supplierSignService;
    @Resource
    private ApprovalService approvalService;
    @Resource
    private SupplierService supplierService;

    //供应商签约添加
    @PostMapping("/add")
    public AjaxResult add( @RequestBody Map<String, Object> approvalData)
    {
        Integer supplierId = (Integer) approvalData.get("supplierId");
        String startTime =approvalData.get("startTime")+ "T00:00:00+08:00";;
        String endTime =approvalData.get("endTime")+ "T00:00:00+08:00";;
        String settlementPeriod = (String) approvalData.get("settlementPeriod");
        Integer creditLimit = (Integer) approvalData.get("creditLimit");
        String creditLimit1="creditLimit";
        String purchasePerson = (String) approvalData.get("purchasePerson");
        String purchaseDepartment = (String) approvalData.get("purchaseDepartment");
        String agreement = (String) approvalData.get("agreement");
        String approvalTime = (String) approvalData.get("approvalTime");
        approvalData.get("approver");
        Integer approvalId = (Integer) approvalData.get("approvalId");
        String reason = (String) approvalData.get("reason");

        OffsetDateTime odt = OffsetDateTime.parse(approvalTime);
        // 将OffsetDateTime转换为Instant
        java.time.Instant instant = odt.toInstant();
        Date approvalTimeDate = Date.from(instant);


// 创建 LambdaUpdateWrapper 对象用于构建更新条件
        LambdaUpdateWrapper<Approval> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(Approval::getStatus, 0)
                .eq(Approval::getAimId, supplierId) // 假设 supplierId 已经在上下文中定义
                .set(Approval::getReason, ""); // 如果需要更新 reason 字段为 ""

// 根据 agreement 的值决定更新哪些字段
        if ("同意".equals(agreement)) {
            updateWrapper.set(Approval::getStatus, true)
                    .set(Approval::getTime, approvalTimeDate)
                    .set(Approval::getUserinfoId, approvalId);
            Supplier supplier = supplierService.getById(supplierId).setSupplierState("合格供应商");
            supplierService.updateById(supplier);
        }else {
            updateWrapper.set(Approval::getStatus, true)
                    .set(Approval::getTime, approvalTimeDate)
                    .set(Approval::getUserinfoId, approvalId)
                    .set(Approval::getReason,reason);
        }
        // 调用 approvalService 的 update 方法执行更新操作
        approvalService.update(updateWrapper);
        //添加一条签约信息
        OffsetDateTime odt1 = OffsetDateTime.parse(startTime);
        // 将OffsetDateTime转换为Instant
        java.time.Instant instant1 = odt1.toInstant();
        Date startTimeDateTime = Date.from(instant1);
        OffsetDateTime odt2 = OffsetDateTime.parse(endTime);
        // 将OffsetDateTime转换为Instant
        java.time.Instant instant2 = odt2.toInstant();
        Date endTimeDateTime = Date.from(instant2);
        SupplierSign supplierSign = new SupplierSign();
        supplierSign.setSupplierId(supplierId);
        supplierSign.setStartTime(startTimeDateTime);
        supplierSign.setEndTime(endTimeDateTime);
        supplierSign.setSupplierSignDeadline(settlementPeriod);
        supplierSign.setCreditLimit(creditLimit1);
        supplierSign.setPurchasePerson(purchasePerson);
        supplierSign.setPurchaseDepartment(purchaseDepartment);
        supplierSignService.save(supplierSign);


        return AjaxResult.success("成功");
    }


}
