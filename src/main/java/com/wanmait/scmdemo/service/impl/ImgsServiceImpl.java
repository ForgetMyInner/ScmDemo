package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.Imgs;
import com.wanmait.scmdemo.mapper.ImgsMapper;
import com.wanmait.scmdemo.service.ImgsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 附件图片表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ImgsServiceImpl extends ServiceImpl<ImgsMapper, Imgs> implements ImgsService {

}
