package com.wanmait.scmdemo.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wanmait.scmdemo.entity.SupplierEnterRule;
import com.wanmait.scmdemo.entity.SupplierEnterRuleDTO;
import com.wanmait.scmdemo.entity.SupplierEnterRuleData;
import com.wanmait.scmdemo.service.SupplierEnterRuleDataService;
import com.wanmait.scmdemo.service.SupplierEnterRuleService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商准入条例 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/supplierEnterRule")
public class SupplierEnterRuleController {
    @Resource
    private SupplierEnterRuleService supplierEnterRuleService;
    @Resource
    private SupplierEnterRuleDataService supplierEnterRuleDataService;

    /*添加一个名称对应多条准则的条例*/
    @PostMapping("add")
    public AjaxResult insertRule(@RequestBody SupplierEnterRuleDTO supplierEnterRuleDTO) {
        System.out.println("Test------>>>>>>" + supplierEnterRuleDTO.getSupplierEnterRule().getName() + "----" + supplierEnterRuleDTO.getSupplierEnterRule().getTotalScore());
        System.out.println("Test------>>>>>>" + supplierEnterRuleDTO);

        SupplierEnterRule supplierEnterRule = supplierEnterRuleDTO.getSupplierEnterRule();
        supplierEnterRuleService.save(supplierEnterRule);
        Integer pid = supplierEnterRule.getId();//唯一
        List<SupplierEnterRuleData> supplierEnterRuleDataList = supplierEnterRuleDTO.getSupplierEnterRuleDataList();
        supplierEnterRuleDataList.forEach(supplierEnterRuleData -> {
            supplierEnterRuleData.setEnterRuleId(pid);
        });
        supplierEnterRuleDataService.saveBatch(supplierEnterRuleDataList);
        return AjaxResult.success("添加成功");
    }

    //    @Cacheable(value = "commonCache", key = "'supplierEnterRule:'+#supplierEnterRule")
    @GetMapping("list")
    public AjaxResult findAll() {

        //获取准入条例 列表
        List<SupplierEnterRule> supplierEnterRules = supplierEnterRuleService.list();
        /*创建一个存放两个对象的DTO*/
        List<SupplierEnterRuleDTO> supplierEnterRuleDTOList = new ArrayList<>();

        for (SupplierEnterRule supplierEnterRule : supplierEnterRules) {

            /*给每一个DTO条例赋值id  DTO的id准入条例的id*/
            SupplierEnterRuleDTO supplierEnterRuleDTO = new SupplierEnterRuleDTO();

            //取出每条条例的id 并设置id
            Integer id = supplierEnterRule.getId();
            supplierEnterRuleDTO.setId(id);
            //设置条例对象
            supplierEnterRuleDTO.setSupplierEnterRule(supplierEnterRule);

            Map<String, Object> map = new HashMap<>();
            map.put("enter_rule_id", id);
            List<SupplierEnterRuleData> supplierEnterRuleDataList = supplierEnterRuleDataService.listByMap(map);  // 请替换为实际的获取逻辑
            //设置条例内的条例规则
            supplierEnterRuleDTO.setSupplierEnterRuleDataList(supplierEnterRuleDataList);

            //添加的list中
            supplierEnterRuleDTOList.add(supplierEnterRuleDTO);
        }
        return AjaxResult.success("查询成功", supplierEnterRuleDTOList);
    }

    /*批量删除*/
    @PostMapping("deleteBatch")
    public AjaxResult deleteBatch(@RequestBody List<Integer> ids) {
        // 删除准入条例规则
        boolean isDeleteSuccess = supplierEnterRuleService.removeByIds(ids);
        if (!isDeleteSuccess) {
            throw new RuntimeException("删除准入条例规则失败");
        }

        // 更新相关的SupplierEnterRuleData
        LambdaUpdateWrapper<SupplierEnterRuleData> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(SupplierEnterRuleData::getEnterRuleId, ids)
                .set(SupplierEnterRuleData::getEnable, 0); // 假设0代表已删除或失效状态

        boolean isUpdateSuccess = supplierEnterRuleDataService.update(updateWrapper);
        if (!isUpdateSuccess) {
            throw new RuntimeException("更新相关数据失败");
        }

        return AjaxResult.success("删除成功");
    }
}
