package com.wanmait.scmdemo.controller;


import com.wanmait.scmdemo.entity.Admin;
import com.wanmait.scmdemo.service.AdminService;
import com.wanmait.scmdemo.util.AjaxResult;
import com.wanmait.scmdemo.util.IPUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/manage")
public class CommonController {
    @Resource
    private AdminService adminService;

    @PostMapping("login")
    public AjaxResult login(@RequestBody Admin admin, HttpServletRequest request){
        System.out.println("*************");
        admin.setLoginIp(IPUtils.getClientIpAddr(request));
        return adminService.login(admin);
    }
}
