package com.wanmait.scmdemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class AttachmentServiceTest {
    @Resource
    private AttachmentService attachmentService;
    @Test
    public void testCity(){
        System.out.println(attachmentService.findBySupplierId(30));
    }
}
