package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.Warehouse;
import com.wanmait.scmdemo.service.WarehouseService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 仓库 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Resource
    private WarehouseService warehouseService;

    //添加仓库
    //@RequestBody 必须为post提交方式或者put提交方式,只能有一个参数前面加@RequestBody
    @PostMapping("add")
    public AjaxResult add(@RequestBody Warehouse warehouse)
    {
        warehouseService.save(warehouse);
        return AjaxResult.success("添加成功",warehouse);
    }
    //查询仓库
    @GetMapping("list")
    public AjaxResult list()
    {
        return AjaxResult.success("查询成功",warehouseService.list());
    }
    //通过仓库id查询仓库
    @GetMapping("{id:\\d+}")
    public AjaxResult findById(@PathVariable Integer id)
    {
        return AjaxResult.success("查询成功",warehouseService.getById(id));
    }

}
