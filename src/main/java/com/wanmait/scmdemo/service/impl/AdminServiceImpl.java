package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.Admin;
import com.wanmait.scmdemo.mapper.AdminMapper;
import com.wanmait.scmdemo.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 管理员用户表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
