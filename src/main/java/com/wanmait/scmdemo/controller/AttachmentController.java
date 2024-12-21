package com.wanmait.scmdemo.controller;

import com.wanmait.scmdemo.service.AttachmentService;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 附件表 前端控制器
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@RestController
@RequestMapping("/manage/attachment")
public class AttachmentController {
    @Resource
    private AttachmentService attachmentService;
    @GetMapping("{id:\\d+}")
    public AjaxResult getAttachment(@PathVariable Integer id){
        return AjaxResult.success("查找成功").put("attachment",attachmentService.findBySupplierId(id));
    }


}
