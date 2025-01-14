package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.Config;
import com.wanmait.scmdemo.mapper.ConfigMapper;
import com.wanmait.scmdemo.service.ConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2025-01-07
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {
    @Resource
    private ConfigMapper configMapper;
    @Override
    public Map<String, String> listKeyValueMap() {
        List<Config> configs = configMapper.selectList(null);

        Map<String,String> map = new HashMap<>();
        configs.forEach(config -> map.put(config.getConfigKey(),config.getConfigValue()));
        return map;
    }

}
