package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.service.ContactinfoService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 供应商的联系人信息表 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/contactinfo")
public class ContactinfoController {
    @Resource
    private ContactinfoService contactinfoService;
    @GetMapping("{id:\\d+}")
    public AjaxResult getContactinfo(@PathVariable Integer id){
        return AjaxResult.success("查找成功").put("contactinfo",contactinfoService.findBySupplierId(id));
    }



}
