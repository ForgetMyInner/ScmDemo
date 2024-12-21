package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.ScmDictData;
import com.wanmait.scmdemo.entity.Warehouse;
import com.wanmait.scmdemo.mapper.ScmDictDataMapper;
import com.wanmait.scmdemo.service.ScmDictDataService;
import com.wanmait.scmdemo.service.WarehouseService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 仓库 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController//相当于@Controller+@ResponseBody(所有的函数都不转发)
@RequestMapping("/warehouse")
public class WarehouseController {
    @Resource
    private WarehouseService warehouseService;
    //添加仓库
    //application.json形式
    //@RequestBody 必须为post提交方式或者put提交方式,只能有一个参数前面加@RequestBody
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Warehouse warehouse)//+@RequestBody不加接收不到数据
    {
        //生成一个12位随机的仓库编码,添加到对象中
        StringBuilder code1 = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            code1.append(random.nextInt(10));
        }
        String code = code1.toString();


        //随机12位仓库编码
        warehouse.setCode(code);
        warehouse.setEnable(true);

        warehouseService.save(warehouse);
        return AjaxResult.success("添加成功",warehouse);
    }

    //查询仓库
    @GetMapping("/list")
    public AjaxResult list()
    {
        return AjaxResult.success("查询成功",warehouseService.list());
    }
    //通过仓库id查询仓库
    @GetMapping("/{id:\\d+}")//利用正则表达式限制,传的id为数字
    public AjaxResult findById(@PathVariable Integer id)//放在路径里边需要+@PathVariable
    {
        return AjaxResult.success("查询成功",warehouseService.getById(id));
    }

}
