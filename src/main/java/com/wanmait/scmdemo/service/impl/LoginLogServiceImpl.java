package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.LoginLog;
import com.wanmait.scmdemo.mapper.LoginLogMapper;
import com.wanmait.scmdemo.service.LoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员登录记录表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

}
