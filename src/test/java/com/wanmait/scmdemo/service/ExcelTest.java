package com.wanmait.scmdemo.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.wanmait.scmdemo.entity.ProductWarehouse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class ExcelTest {

    @Test
    public void testExcel() {
        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");

        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);


//通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeTest.xlsx");
//通过构造方法创建writer
//ExcelWriter writer = new ExcelWriter("d:/writeTest.xls");

//跳过当前行，既第一行，非必须，在此演示用
        writer.passCurrentRow();

//合并单元格后的标题行，使用默认标题样式
        writer.merge(row1.size() - 1, "测试标题");
//一次性写出内容，强制输出标题
        writer.write(rows, true);
//关闭writer，释放内存
        writer.close();

    }

    @Test
    public void testExcelMap() {
        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("姓名", "张三");
        row1.put("年龄", 23);
        row1.put("成绩", 88.32);
        row1.put("是否合格", true);
        row1.put("考试日期", DateUtil.date());

        Map<String, Object> row2 = new LinkedHashMap<>();
        row2.put("姓名", "李四");
        row2.put("年龄", 33);
        row2.put("成绩", 59.50);
        row2.put("是否合格", false);
        row2.put("考试日期", DateUtil.date());

        ArrayList<Map<String, Object>> rows = CollUtil.newArrayList(row1, row2);
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeMapTestMap.xlsx");
// 合并单元格后的标题行，使用默认标题样式
        writer.merge(row1.size() - 1, "一班成绩单");
// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
// 关闭writer，释放内存
        writer.close();
    }

    @Test
    public void testExcelBean() {
        ProductWarehouse productWarehouse1 = new ProductWarehouse();
        ProductWarehouse productWarehouse2 = new ProductWarehouse();

        productWarehouse1.setId(1).setProductShow(2).setProductShowEntity(null).setEnterTime(new Date()).setEnterPerson("张三");
        productWarehouse2.setId(2).setProductShow(5).setProductShowEntity(null).setEnterTime(new Date()).setEnterPerson("李四");
        List<ProductWarehouse> rows = CollUtil.newArrayList(productWarehouse1, productWarehouse2);
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeBeanTestBean.xlsx");
// 合并单元格后的标题行，使用默认标题样式
        writer.merge(4, "一班成绩单");
// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
// 关闭writer，释放内存
        writer.close();


    }

    @Test
    public void testExcelBean2() {
        ProductWarehouse productWarehouse1 = new ProductWarehouse();
        ProductWarehouse productWarehouse2 = new ProductWarehouse();

        productWarehouse1.setId(1).setProductShow(2).setProductShowEntity(null).setEnterTime(new Date()).setEnterPerson("张三");
        productWarehouse2.setId(2).setProductShow(5).setProductShowEntity(null).setEnterTime(new Date()).setEnterPerson("李四");
        List<ProductWarehouse> rows = CollUtil.newArrayList(productWarehouse1, productWarehouse2);

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/writeBeanTestBean2.xlsx");

//自定义标题别名
        writer.addHeaderAlias("enable", "是否删除");
        writer.addHeaderAlias("updateTime", "更新时间");

// 默认的，未添加alias的属性也会写出，如果想只写出加了别名的字段，可以调用此方法排除之
        writer.setOnlyAlias(true);

// 合并单元格后的标题行，使用默认标题样式
        writer.merge(8, "一班成绩单");
// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
// 关闭writer，释放内存
        writer.close();

    }
}
