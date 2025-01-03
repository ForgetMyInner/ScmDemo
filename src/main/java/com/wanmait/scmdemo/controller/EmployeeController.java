package com.wanmait.scmdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanmait.scmdemo.entity.Employee;
import com.wanmait.scmdemo.service.EmployeeService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
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

    @GetMapping("/listByDepartmentId/{departmentId:\\d+}")

    public AjaxResult listByDepartmentId(@PathVariable Integer departmentId) {
        System.out.println("部门id："+departmentId);
        LambdaQueryWrapper <Employee> QueryWrapper = new LambdaQueryWrapper<>();
        QueryWrapper.eq(Employee::getCompanyDepartmentId,departmentId);
        List<Employee> employees = employeeService.list(QueryWrapper);
        System.out.println("查询结果："+employees);
        if (employees != null) {
            return AjaxResult.success("查询员工成功", employees);
        } else {
            return AjaxResult.error("部门不存在或没有员工");
        }
    }

}
