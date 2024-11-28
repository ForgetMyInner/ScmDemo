package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.DeliverPerson;
import com.wanmait.scmdemo.mapper.DeliverPersonMapper;
import com.wanmait.scmdemo.service.DeliverPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 送货联系人 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeliverPersonServiceImpl extends ServiceImpl<DeliverPersonMapper, DeliverPerson> implements DeliverPersonService {

}
