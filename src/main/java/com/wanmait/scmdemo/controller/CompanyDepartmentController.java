package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.service.CompanyDepartmentService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 公司部门表 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/companyDepartment")
public class CompanyDepartmentController {
    @Resource
    private CompanyDepartmentService companyDepartmentService;
    //查询所有部门
    @GetMapping("/list")

    public AjaxResult list()
    {
        return AjaxResult.success("查询成功",companyDepartmentService.list());
    }

}
