package com.wanmait.scmdemo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.wanmait.scmdemo.entity.SampleSubmissionDto;
import com.wanmait.scmdemo.entity.SampleTable;
import com.wanmait.scmdemo.entity.Supplier;
import com.wanmait.scmdemo.entity.SupplierSampleManagement;
import com.wanmait.scmdemo.service.SampleTableService;
import com.wanmait.scmdemo.service.SupplierSampleManagementService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 样品表 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-12-28
 */
@RestController
@RequestMapping("manage/sampleTable")
public class SampleTableController {

    @Resource
    private SampleTableService sampleTableService;
    @Resource
    private SupplierSampleManagementService supplierSampleManagementService;

    @Autowired
    private ObjectMapper objectMapper;


    //查询所有
    @PostMapping("/add")
    public void add(@RequestBody Map<String, Object> paylod) {
        Supplier supplierEntity=null;
        SupplierSampleManagement ssmEntity=null;
        List<SampleTable> sampleTableList=null;

        // 提取 contactInfo
        Object supplierObj = paylod.get("Supplier");
        if (supplierObj instanceof Map) {
            Map<String, Object> supplier = (Map<String, Object>) supplierObj;
            // 将 Map<String, Object> 类型的 supplier 转换为 Supplier 类型
            supplierEntity = convertMapToSupplier(supplier);
            // 这里可以对转换后的 supplierEntity 进行操作，例如保存到数据库或其他处理

        } else if (supplierObj instanceof ArrayList) {
            ArrayList<Object> supplierList = (ArrayList<Object>) supplierObj;
            // 处理 supplierObj 是 ArrayList 的情况，根据需要修改处理逻辑
            for (Object item : supplierList) {
                if (item instanceof Map) {
                    Map<String, Object> itemMap = (Map<String, Object>) item;
                    // 将 Map<String, Object> 类型的 itemMap 转换为 Supplier 类型
                    supplierEntity = convertMapToSupplier(itemMap);
                    // 处理转换后的 supplierEntity
                    System.out.println(supplierEntity.getSupplierName()+"3");
                } else {
                    // 处理列表中的其他元素，根据需要修改处理逻辑
                    System.out.println("Unexpected item type: " + item.getClass()+"2");
                }
            }
        }

        // 提取 supplierSampleManagement
        Object supplierSampleManagementObj = paylod.get("SupplierSampleManagement");
        if (supplierSampleManagementObj instanceof Map) {
            Map<String, Object> supplierSampleManagement = (Map<String, Object>) supplierSampleManagementObj;
            // 将 Map<String, Object> 类型的 supplierSampleManagement 转换为 SupplierSampleManagement 类型
            ssmEntity= convertMapToSupplierSampleManagement(supplierSampleManagement);
            // 这里可以对转换后的 ssmEntity 进行操作，例如保存到数据库或其他处理
            ssmEntity.setSupplierId(supplierEntity.getId());
        } else if (supplierSampleManagementObj instanceof ArrayList) {
            ArrayList<Object> supplierSampleManagementList = (ArrayList<Object>) supplierSampleManagementObj;
            // 处理 supplierSampleManagementObj 是 ArrayList 的情况，根据需要修改处理逻辑
            for (Object item : supplierSampleManagementList) {
                if (item instanceof Map) {
                    Map<String, Object> itemMap = (Map<String, Object>) item;
                    // 将 Map<String, Object> 类型的 itemMap 转换为 SupplierSampleManagement 类型
                    ssmEntity = convertMapToSupplierSampleManagement(itemMap);
                    // 处理转换后的 ssmEntity
                    System.out.println(ssmEntity.getMailingCode() + "3");
                } else {
                    // 处理列表中的其他元素，根据需要修改处理逻辑
                    System.out.println("Unexpected item type: " + item.getClass() + "2");
                }
            }
        }

        // 提取 SampleTables
        Object sampleTablesObj = paylod.get("eTables");
        if (sampleTablesObj instanceof List) {
            List<Map<String, Object>> sampleTables = (List<Map<String, Object>>) sampleTablesObj;
            // 将 List<Map<String, Object>> 类型的 sampleTables 转换为 List<SampleTable> 类型
            sampleTableList = convertListOfMapsToSampleTableList(sampleTables);

        } else {
            // 处理 sampleTablesObj 不是 List 类型的情况，根据需要修改处理逻辑
            System.out.println("Unexpected item type: " + sampleTablesObj.getClass());
        }

        if(sampleTableList.get(0).getProductCode()!="")
        {

            ssmEntity.setIsnew(true);

            SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
            // 将 Date 对象格式化为目标格式的字符串
            try {
                String targetDateStr = targetFormat.format(ssmEntity.getMailingTime());
                ssmEntity.setMailingTime(targetFormat.parse(targetDateStr));
            } catch (ParseException e) {
                // 打印异常信息，可根据实际需求进行异常处理
                e.printStackTrace();
            }
            supplierSampleManagementService.save(ssmEntity);
            List<SupplierSampleManagement> supplierSampleManagements=supplierSampleManagementService.selectSupplierSampleManagement(ssmEntity);
            for (SampleTable sampleTable : sampleTableList) {
                sampleTable.setId(null);
                sampleTable.setSupplierSampleManagementId(supplierSampleManagements.get(0).getId());

                sampleTableService.save(sampleTable);
            }
        }
        else {
            ssmEntity.setIsnew(false);


            SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
            // 将 Date 对象格式化为目标格式的字符串
            try {
                String targetDateStr = targetFormat.format(ssmEntity.getMailingTime());
                ssmEntity.setMailingTime(targetFormat.parse(targetDateStr));
            } catch (ParseException e) {
                // 打印异常信息，可根据实际需求进行异常处理
                e.printStackTrace();
            }
            supplierSampleManagementService.save(ssmEntity);
            List<SupplierSampleManagement> supplierSampleManagements=supplierSampleManagementService.selectSupplierSampleManagement(ssmEntity);
            for (SampleTable sampleTable : sampleTableList) {
                Random random = new Random();
                StringBuilder code = new StringBuilder();
                // 生成两位大写字母
                for (int i = 0; i < 2; i++)
                {
                    char c = (char) (random.nextInt(26) + 'A');
                    code.append(c);
                }
                // 生成六位数字
                for (int i = 0; i < 4; i++)
                {
                    code.append(random.nextInt(10));
                }
                sampleTable.setProductCode(code+"");
                sampleTable.setSupplierSampleManagementId(supplierSampleManagements.get(0).getId());
                sampleTableService.save(sampleTable);
            }
        }


    }
    private Supplier convertMapToSupplier(Map<String, Object> supplierMap) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(supplierMap, Supplier.class);
    }
    private SupplierSampleManagement convertMapToSupplierSampleManagement(Map<String, Object> supplierSampleManagementMap) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        // 配置日期反序列化器
        LocalDateTimeDeserializer dateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        module.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
        mapper.registerModule(module);
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper.convertValue(supplierSampleManagementMap, SupplierSampleManagement.class);

    }
    private List<SampleTable> convertListOfMapsToSampleTableList(List<Map<String, Object>> sampleTables) {
        List<SampleTable> sampleTableList = new ArrayList<>();
        for (Map<String, Object> sampleTableMap : sampleTables) {
            SampleTable sampleTable = objectMapper.convertValue(sampleTableMap, SampleTable.class);
            sampleTableList.add(sampleTable);
        }
        return sampleTableList;
    }
}