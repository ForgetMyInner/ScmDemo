package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.Log;
import com.wanmait.scmdemo.mapper.LogMapper;
import com.wanmait.scmdemo.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-12-14
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
