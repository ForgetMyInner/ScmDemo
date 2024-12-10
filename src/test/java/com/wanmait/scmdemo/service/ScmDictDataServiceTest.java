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
        List<ScmDictData> list = scmDictDataService.list("other_out_warehouse");
        list.forEach(System.out::println);
    }
}
