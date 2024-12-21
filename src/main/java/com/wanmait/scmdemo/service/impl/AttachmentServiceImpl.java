package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanmait.scmdemo.entity.Attachment;
import com.wanmait.scmdemo.mapper.AttachmentMapper;
import com.wanmait.scmdemo.service.AttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 附件表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-12-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {
    @Resource
    private AttachmentMapper attachmentMapper;

    @Override
    public AjaxResult findBySupplierId(int id) {
        LambdaQueryWrapper<Attachment> attachmentLambdaQueryWrapper=new LambdaQueryWrapper<>();
        attachmentLambdaQueryWrapper.eq(Attachment::getSupplierId,id);
        Attachment attachment = attachmentMapper.selectOne(attachmentLambdaQueryWrapper);
        return AjaxResult.success("查询成功").put("attachment",attachment);
    }
}
