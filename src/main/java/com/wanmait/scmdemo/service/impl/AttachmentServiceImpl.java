package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.Attachment;
import com.wanmait.scmdemo.mapper.AttachmentMapper;
import com.wanmait.scmdemo.service.AttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
