package com.wanmait.scmdemo.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.wanmait.scmdemo.entity.ProductWarehouse;
import com.wanmait.scmdemo.service.ProductWarehouseService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * <p>
 * 产品在库存 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/productWarehouse")
public class ProductWarehouseController {

    @Resource
    private ProductWarehouseService productWarehouseService;

    /*仓库辅助表首页 list*/
    @GetMapping("/assistant/list")
    public AjaxResult list(int pageNum, int pageSize) {
        return AjaxResult.success(productWarehouseService.assistantPageList(pageNum, pageSize));
    }

    /*下载 table信息*/
    @PostMapping("/assistant/downloadData")
    public AjaxResult downloadData(@RequestBody List<ProductWarehouse> warehouseAssistantDTOs) {
//        if (warehouseAssistantDTOs.size() < 1) {
//            return AjaxResult.error("请选择数据");
//        }
        try {

            List<ProductWarehouse> rows = CollUtil.newArrayList(warehouseAssistantDTOs);

            // 获取用户主目录并创建下载路径
            String userHome = System.getProperty("user.home");
            Path downloadPath = Paths.get(userHome, "Downloads", "仓库辅助表.xlsx");
            String filePath = downloadPath.toString();

            // 创建Excel写入器并指定文件路径
            ExcelWriter writer = ExcelUtil.getWriter(filePath);

            // 自定义标题别名
            writer.addHeaderAlias("warehouseEntity.name", "仓库名称");
            writer.addHeaderAlias("productShowEntity.productName", "产品名称");
            writer.addHeaderAlias("productShowEntity.productCode", "产品编码");
            writer.addHeaderAlias("productShowEntity.productBrand", "品牌");
            writer.addHeaderAlias("productShowEntity.productModels", "规格型号");
            writer.addHeaderAlias("productShowEntity.productUnit", "单位");
            writer.addHeaderAlias("enterPerson", "录入人");
            writer.addHeaderAlias("productShowEntity.enterTime", "录入时间");  // 使用productShowEntity中的enterTime
            writer.addHeaderAlias("productShowEntity.updateTime", "更新时间");  // 使用productShowEntity中的updateTime

            // 只导出设置了别名的字段
            writer.setOnlyAlias(true);

            // 合并单元格后的标题行，使用默认标题样式
            writer.merge(9, "仓库辅助表");  // 注意：这里应该是9列，因为有10个字段

            // 一次性写出内容，使用默认样式，强制输出标题
            writer.write(rows, true);

            // 关闭writer，释放内存
            writer.close();

            System.out.println("文件已成功导出到: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("导出文件时发生错误: " + e.getMessage());
        }
        return AjaxResult.success("下载成功");

    }
}
