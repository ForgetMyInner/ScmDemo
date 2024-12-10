package com.wanmait.scmdemo.controller;

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
import java.util.stream.Collectors;

/**
 * <p>
 * 准则条例中的>>>多个评估项 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/supplierEnterRuleData")
public class SupplierEnterRuleDataController {
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
        Integer pid = supplierEnterRule.getId();
        List<SupplierEnterRuleData> supplierEnterRuleDataList = supplierEnterRuleDTO.getSupplierEnterRuleDataList();
        supplierEnterRuleDataList.forEach(supplierEnterRuleData -> {
            supplierEnterRuleData.setEnterRuleId(pid);
        });
        supplierEnterRuleDataService.saveBatch(supplierEnterRuleDataList);
        return AjaxResult.success("添加成功");
    }

    @GetMapping("list")
    public AjaxResult findAll() {
        //获取准入条例 列表
        List<SupplierEnterRule> supplierEnterRules = supplierEnterRuleService.list();
        /*创建一个存放两个对象的DTO*/
        List<SupplierEnterRuleDTO> supplierEnterRuleDTOList = new ArrayList<>();

        for (SupplierEnterRule supplierEnterRule : supplierEnterRules) {

            SupplierEnterRuleDTO supplierEnterRuleDTO = new SupplierEnterRuleDTO();

            //取出每条条例的id 并设置id
            Integer id = supplierEnterRule.getId();
            supplierEnterRuleDTO.setId(id);
            //设置条例对象
            supplierEnterRuleDTO.setSupplierEnterRule(supplierEnterRule);

            Map<String, Object> map = new HashMap<>();
            map.put("enter_rule_id", id);
            List<SupplierEnterRuleData> supplierEnterRuleDataList = supplierEnterRuleDataService.listByMap(map);  // 请替换为实际的获取逻辑
            //设置多个小条例列表
            supplierEnterRuleDTO.setSupplierEnterRuleDataList(supplierEnterRuleDataList);
            //添加的list中
            supplierEnterRuleDTOList.add(supplierEnterRuleDTO);
        }
        return AjaxResult.success("查询成功", supplierEnterRuleDTOList);
    }

}
