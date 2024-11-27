package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.SysNotice;
import com.wanmait.scmdemo.mapper.SysNoticeMapper;
import com.wanmait.scmdemo.service.SysNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 通知公告表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService {

}
