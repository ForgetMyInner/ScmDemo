package com.wanmait.scmdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.wanmait.scmdemo.dto.PurchaseNeedPoolDTO;
import com.wanmait.scmdemo.entity.*;
import com.wanmait.scmdemo.service.*;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manage/purchaseProductDetails")
public class PurchaseProductDetailsController {

    @Autowired
    private PurchaseProductDetailsService purchaseProductDetailsService;
    @Autowired
    private ProductWarehouseService productWarehouseService;
    @Autowired
    private ProductSupplierService productSupplierService;
    @Autowired
    private ProductShowService productShowService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private PurchaseApplyService purchaseApplyService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CompanyDepartmentService companyDepartmentService;
    @Autowired
    private ConfigService configService;

    @GetMapping("/listAll")
    public AjaxResult listAll(@RequestBody PurchaseProductDetails purchaseProductDetails, Integer pageNum, Integer pageSize) {
        if (pageNum==null){
            pageNum = 1;
        }
        try {
            if (pageSize==null){
                pageSize=Integer.parseInt(configService.listKeyValueMap().get("manage_pagesize"));
            }
        }catch (NumberFormatException e){
            pageSize=10;
        }
        PageInfo <PurchaseProductDetails> purchaseProductDetailsPageInfo = purchaseProductDetailsService.purchaseProductDetailsWithAttachAndContact(purchaseProductDetails, pageNum, pageSize);
        return AjaxResult.success("查询成功").put("查询成功",purchaseProductDetailsPageInfo);
    }

    @GetMapping("/list")
    public AjaxResult list(@RequestParam(defaultValue = "0") int filterCriteria,@RequestParam (defaultValue = "") String conditions,@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
        // 创建分页对象
        Page<PurchaseProductDetails> purchaseProductDetailsPage = new Page<>(page, pageSize);
        // 执行分页查询
        IPage<PurchaseProductDetails> purchaseProductDetailsIPage = purchaseProductDetailsService.page(purchaseProductDetailsPage);
        List<PurchaseProductDetails> purchaseProductDetailsList = purchaseProductDetailsIPage.getRecords();

        List<PurchaseNeedPoolDTO> purchaseNeedPoolDTOList = new ArrayList<>();
        for (PurchaseProductDetails details : purchaseProductDetailsList) {
            PurchaseNeedPoolDTO purchaseNeedPoolDTO = new PurchaseNeedPoolDTO();

            // 产品在库存
            LambdaQueryWrapper<ProductWarehouse> productWarehouseWrapper = new LambdaQueryWrapper<>();
            productWarehouseWrapper.eq(ProductWarehouse::getId, details.getProductWarehouseId());
            ProductWarehouse productWarehouse = productWarehouseService.getOne(productWarehouseWrapper);

            // 产品 - 供应商关系表
            LambdaQueryWrapper<ProductSupplier> productSupplierWrapper = new LambdaQueryWrapper<>();
            productSupplierWrapper.eq(ProductSupplier::getId, details.getProductSupplierId());
            ProductSupplier productSupplier = productSupplierService.getOne(productSupplierWrapper);

            // 产品表
            LambdaQueryWrapper<ProductShow> productShowWrapper = new LambdaQueryWrapper<>();
            productShowWrapper.eq(ProductShow::getId, productSupplier.getProductShowId());
            ProductShow productShow = productShowService.getOne(productShowWrapper);

            // 供应商
            LambdaQueryWrapper<Supplier> supplierWrapper = new LambdaQueryWrapper<>();
            supplierWrapper.eq(Supplier::getId, productSupplier.getSupplierId());
            Supplier supplier = supplierService.getOne(supplierWrapper);

            // 仓库表
            LambdaQueryWrapper<Warehouse> warehouseWrapper = new LambdaQueryWrapper<>();
            warehouseWrapper.eq(Warehouse::getId, productWarehouse.getWarehouseId());
            Warehouse warehouse = warehouseService.getOne(warehouseWrapper);

            // 采购申请表
            LambdaQueryWrapper<PurchaseApply> purchaseApplyWrapper = new LambdaQueryWrapper<>();
            purchaseApplyWrapper.eq(PurchaseApply::getId, details.getPurchaseApplyId());
            PurchaseApply purchaseApply = purchaseApplyService.getOne(purchaseApplyWrapper);

            // 采购申请的员工
            LambdaQueryWrapper<Employee> employeeWrapper = new LambdaQueryWrapper<>();
            employeeWrapper.eq(Employee::getId, purchaseApply.getEmployeeId());
            Employee employee = employeeService.getOne(employeeWrapper);

            // 部门表
            if (employee != null) {
                LambdaQueryWrapper<CompanyDepartment> companyDepartmentWrapper = new LambdaQueryWrapper<>();
                companyDepartmentWrapper.eq(CompanyDepartment::getId, employee.getCompanyDepartmentId());
                CompanyDepartment companyDepartment = companyDepartmentService.getOne(companyDepartmentWrapper);
                purchaseNeedPoolDTO.setCompanyDepartment(companyDepartment);
            } else {
                purchaseNeedPoolDTO.setCompanyDepartment(null);
            }
            purchaseNeedPoolDTO.setPurchaseProductDetails(details);
            purchaseNeedPoolDTO.setProductShow(productShow);
            purchaseNeedPoolDTO.setWarehouse(warehouse);
            purchaseNeedPoolDTO.setPurchaseApply(purchaseApply);
            purchaseNeedPoolDTO.setSupplier(supplier);
            purchaseNeedPoolDTO.setProductWarehouse(productWarehouse);
            purchaseNeedPoolDTO.setProductSupplier(productSupplier);
            purchaseNeedPoolDTO.setEmployee(employee);
            purchaseNeedPoolDTOList.add(purchaseNeedPoolDTO);
        }

        // 根据 filterCriteria 和 conditions 过滤结果
        if (!conditions.isEmpty()) {
            switch (filterCriteria) {
                case 1:
                    // 假设 filterCriteria 为 1 时，根据产品名称过滤
                    purchaseNeedPoolDTOList = purchaseNeedPoolDTOList.stream()
                            .filter(dto -> dto.getProductShow() != null && dto.getProductShow().getProductName().contains(conditions))
                            .collect(Collectors.toList());
                    break;
                case 2:
                    // 假设 filterCriteria 为 2 时，根据供应商名称过滤
                    purchaseNeedPoolDTOList = purchaseNeedPoolDTOList.stream()
                            .filter(dto -> dto.getSupplier() != null && dto.getSupplier().getSupplierName().contains(conditions))
                            .collect(Collectors.toList());
                    break;
                case 3:
                    // 假设 filterCriteria 为 3 时，根据采购申请表的采购申请编号过滤
                    purchaseNeedPoolDTOList = purchaseNeedPoolDTOList.stream()
                            .filter(dto -> dto.getPurchaseApply() != null && dto.getPurchaseApply().getCode().contains(conditions))
                            .collect(Collectors.toList());
                    break;
                case 4:
                    // 假设 filterCriteria 为 4 时，根据仓库名
                    purchaseNeedPoolDTOList = purchaseNeedPoolDTOList.stream()
                            .filter(dto -> dto.getWarehouse() != null && dto.getWarehouse().getName().contains(conditions))
                            .collect(Collectors.toList());
                    break;
                default:
                    break;
            }
        }
        // 封装分页结果
        Page<PurchaseNeedPoolDTO> resultPage = new Page<>(page, pageSize, purchaseProductDetailsIPage.getTotal());
        resultPage.setRecords(purchaseNeedPoolDTOList);
        return AjaxResult.success("查询成功", resultPage);
    }

    @GetMapping("displayed")
    public AjaxResult displayed() {
        purchaseProductDetailsService.list();
        return AjaxResult.success("查询成功");
    }
}