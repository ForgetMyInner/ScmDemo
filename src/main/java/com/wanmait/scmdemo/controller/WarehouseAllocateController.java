package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.service.CompanyDepartmentService;
import com.wanmait.scmdemo.service.ScmDictDataService;
import com.wanmait.scmdemo.service.WarehouseService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 库存调拨表 前端控制器
 * </p>
 *
 * @author lzq
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/warehouseAllocate")
public class WarehouseAllocateController {
    @Resource
    private ScmDictDataService scmDictDataService;
    @Resource
    private WarehouseService warehouseService;
    @Resource
    private CompanyDepartmentService companyDepartmentService;

    /**
     * 从数据字典获取所有库存调拨类型
     * @return
     */
    @GetMapping("getTransferTypes")
    public AjaxResult getTransferTypes() {
        return AjaxResult.success(scmDictDataService.list("allocate_type"));
    }

    /**
     * 获取所有仓库
     * @return
     */
    @GetMapping("getWarehouses")
    public AjaxResult getWarehouses() {
        return AjaxResult.success(warehouseService.list());
    }
    /**
     * 获取所有部门
     * @return
     */
    @GetMapping("getDepartments")
    public AjaxResult getDepartments() {
        return AjaxResult.success(companyDepartmentService.list());
    }
}
