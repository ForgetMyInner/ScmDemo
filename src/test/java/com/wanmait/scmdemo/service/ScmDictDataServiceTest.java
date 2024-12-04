package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.ScmDictData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ScmDictDataServiceTest {
    @Resource
    private ScmDictDataService scmDictDataService;
    @Test
    public void testList(){
        List<ScmDictData> list = scmDictDataService.list("supplier_level");
        list.forEach(System.out::println);
    }
}
