package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wanmait.scmdemo.entity.SupplierEnterRule;
import com.wanmait.scmdemo.entity.SupplierEnterRuleDTO;
import com.wanmait.scmdemo.entity.SupplierEnterRuleData;
import com.wanmait.scmdemo.mapper.SupplierEnterRuleDataMapper;
import com.wanmait.scmdemo.mapper.SupplierEnterRuleMapper;
import com.wanmait.scmdemo.service.SupplierEnterRuleDataService;
import com.wanmait.scmdemo.service.SupplierEnterRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商准入条例 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-12-05
 */
@Service
public class SupplierEnterRuleServiceImpl extends ServiceImpl<SupplierEnterRuleMapper, SupplierEnterRule> implements SupplierEnterRuleService {

    @Resource
    private SupplierEnterRuleMapper supplierEnterRuleMapper;
    @Resource
    private SupplierEnterRuleDataMapper supplierEnterRuleDataMapper;

    @Override
    public AjaxResult insertDTO(SupplierEnterRuleDTO supplierEnterRuleDTO) {
        System.out.println("SupplierEnterRule------>>>>>>" + supplierEnterRuleDTO.getSupplierEnterRule().getName() + "----" + supplierEnterRuleDTO.getSupplierEnterRule().getTotalScore());
        System.out.println("Test------>>>>>>" + supplierEnterRuleDTO);

        if (supplierEnterRuleDTO == null || supplierEnterRuleDTO.getSupplierEnterRule() == null || supplierEnterRuleDTO.getSupplierEnterRuleDataList().size() < 1) {
            return AjaxResult.error("添加失败");
        }

        SupplierEnterRule supplierEnterRule = supplierEnterRuleDTO.getSupplierEnterRule();
        supplierEnterRuleMapper.insert(supplierEnterRule);

        Integer pid = supplierEnterRule.getId();//唯一
        List<SupplierEnterRuleData> supplierEnterRuleDataList = supplierEnterRuleDTO.getSupplierEnterRuleDataList();
        supplierEnterRuleDataList.forEach(supplierEnterRuleData -> {
            supplierEnterRuleData.setEnterRuleId(pid);
        });
        supplierEnterRuleDataMapper.insert(supplierEnterRuleDataList);
        return AjaxResult.success("添加成功");
    }

    @Override
    public AjaxResult findAll() {
        //获取准入条例 列表
        List<SupplierEnterRule> supplierEnterRules = supplierEnterRuleMapper.selectList(null);
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

//            Map<String, Object> map = new HashMap<>();
//            map.put("enter_rule_id", id);
            LambdaQueryWrapper<SupplierEnterRuleData> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SupplierEnterRuleData::getEnterRuleId, id);
            List<SupplierEnterRuleData> supplierEnterRuleDataList = supplierEnterRuleDataMapper.selectList(queryWrapper);  // 请替换为实际的获取逻辑
            //设置条例内的条例规则
            supplierEnterRuleDTO.setSupplierEnterRuleDataList(supplierEnterRuleDataList);

            //添加的list中
            supplierEnterRuleDTOList.add(supplierEnterRuleDTO);
        }
        return AjaxResult.success("查询成功", supplierEnterRuleDTOList);
    }

    @Override
    public AjaxResult deleteBatch(List<Integer> ids) {
        if (ids.size() > 0) {
            // 删除准入条例规则
            // supplierEnterRuleMapper.deleteBatchIds(ids);
            // 更新相关的SupplierEnterRuleData
            //LambdaUpdateWrapper<SupplierEnterRuleData> updateWrapper = new LambdaUpdateWrapper<>();
            //updateWrapper.in(SupplierEnterRuleData::getEnterRuleId, ids)
            //       .set(SupplierEnterRuleData::getEnable, 0); // 假设0代表已删除或失效状态
            /*------------------------------------------*/
            LambdaQueryWrapper<SupplierEnterRule> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(SupplierEnterRule::getId, ids);
            supplierEnterRuleMapper.delete(queryWrapper);

            LambdaUpdateWrapper<SupplierEnterRuleData> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.in(SupplierEnterRuleData::getEnterRuleId, ids)
                   .set(SupplierEnterRuleData::getEnable, 0); // 假设0代表已删除或失效状态
            supplierEnterRuleDataMapper.update(updateWrapper);
            return AjaxResult.success("删除成功");

        }
        return AjaxResult.success("删除失败");

    }
}
