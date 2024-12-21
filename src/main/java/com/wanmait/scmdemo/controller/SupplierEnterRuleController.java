package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.SupplierEnterRuleDTO;
import com.wanmait.scmdemo.service.SupplierEnterRuleService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 供应商准入条例 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/supplierEnterRule")
public class SupplierEnterRuleController {
    @Resource
    private SupplierEnterRuleService supplierEnterRuleService;

    /*添加一个名称对应多条准则的条例*/
    @PostMapping("add")
    public AjaxResult insertRule(@RequestBody SupplierEnterRuleDTO supplierEnterRuleDTO) {
        return supplierEnterRuleService.insertDTO(supplierEnterRuleDTO);
    }

    //    @Cacheable(value = "commonCache", key = "'supplierEnterRule:'+#supplierEnterRule")
    @GetMapping("list")
    public AjaxResult findAll() {
        return supplierEnterRuleService.findAll();
    }

    /*批量删除*/
    @PostMapping("deleteBatch")
    public AjaxResult deleteBatch(@RequestBody List<Integer> ids) {
        return supplierEnterRuleService.deleteBatch(ids);
    }
}
