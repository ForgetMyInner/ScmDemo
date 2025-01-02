package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.Supplier;
import com.wanmait.scmdemo.entity.SupplierEnterRoleScores;
import com.wanmait.scmdemo.service.SupplierEnterRoleScoresService;
import com.wanmait.scmdemo.service.SupplierService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @PostMapping("/addScores")
    public AjaxResult addScores(@RequestBody Map<String, Object>  requestBody){
        Integer supplierId = (Integer) requestBody.get("supplierId");
        String clauseName = (String) requestBody.get("clauseName");
        Integer maxScore = (Integer) requestBody.get("maxScore");
        String supplierLevel =(String) requestBody.get("supplierRating");
        List<Map<String, Object>> clauseItems = (List<Map<String, Object>>) requestBody.get("clauseItems");
        supplierEnterRoleScoresService.saveScores(supplierId,clauseName,maxScore,clauseItems,supplierLevel);
        Supplier supplier = supplierService.getById(supplierId);
        if (supplier!=null){
            supplier.setSupplierLevel(supplierLevel);
            supplierService.updateById(supplier);
        }

        return AjaxResult.success("添加成功");
    }


}
