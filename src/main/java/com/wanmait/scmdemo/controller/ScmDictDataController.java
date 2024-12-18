package com.wanmait.scmdemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanmait.scmdemo.entity.ScmDictData;
import com.wanmait.scmdemo.service.ScmDictDataService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/scmDictData")
public class ScmDictDataController {
    @Resource
    private ScmDictDataService scmDictDataService;
    @GetMapping("/dictDataList")
    public AjaxResult list(String dataType){
        return AjaxResult.success("查询成功").put("dataType",scmDictDataService.list(dataType));
    }

//    查询数据库字典
    @GetMapping("/getDictData")
    public List<ScmDictData> getDictDataByLabel(String dictType/*从前端传查询条件*/) {
        LambdaQueryWrapper<ScmDictData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ScmDictData::getDictType, dictType);
        queryWrapper.select(ScmDictData::getDictLabel, ScmDictData::getDictCode);
        System.out.println("查询结果："+ scmDictDataService.list(queryWrapper));
        return scmDictDataService.list(queryWrapper);
    }
}
