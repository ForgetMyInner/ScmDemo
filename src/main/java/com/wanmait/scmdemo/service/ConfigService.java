package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.Config;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanmait
 * @since 2025-01-07
 */
public interface ConfigService extends IService<Config> {
    Map<String,String> listKeyValueMap();
}
