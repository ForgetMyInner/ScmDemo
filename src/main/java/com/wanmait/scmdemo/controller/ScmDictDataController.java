package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.entity.Supplier;
import com.wanmait.scmdemo.service.ScmDictDataService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

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

}
