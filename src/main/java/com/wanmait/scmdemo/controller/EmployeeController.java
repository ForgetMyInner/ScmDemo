package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.service.EmployeeService;
import com.wanmait.scmdemo.service.WarehouseService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 员工表 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    //查询所有员工
    @GetMapping("/list")
    public AjaxResult list()
    {
        return AjaxResult.success("查询成功",employeeService.list());
    }

}
