package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.ScmDictType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ScmDictTypeServiceTest {
    @Resource
    private ScmDictTypeService scmDictTypeService;
    @Test
    public void testList(){
        List<ScmDictType> list = scmDictTypeService.list();
    }

}
