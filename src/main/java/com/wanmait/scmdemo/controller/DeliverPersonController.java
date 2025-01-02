package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.DeliverPerson;
import com.wanmait.scmdemo.service.DeliverPersonService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 送货联系人 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/deliverPerson")
public class DeliverPersonController {
    @Resource
    private DeliverPersonService deliverPersonService;

    //添加送货联系人
    @PostMapping("/add")
    public AjaxResult add(@RequestBody DeliverPerson deliverPerson)
    {
        deliverPersonService.save(deliverPerson);
        return AjaxResult.success("添加成功",deliverPerson);
    }
    //根据id查询送货联系人
    @GetMapping("/{id:\\d+}")//利用正则表达式限制,传的id为数字
    public AjaxResult findById(@PathVariable Integer id)//放在路径里边需要+@PathVariable
    {
        return AjaxResult.success("查询成功",deliverPersonService.getById(id));
    }
}
