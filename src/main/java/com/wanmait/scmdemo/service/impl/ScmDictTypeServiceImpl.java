package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.ScmDictType;
import com.wanmait.scmdemo.mapper.ScmDictTypeMapper;
import com.wanmait.scmdemo.service.ScmDictTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ScmDictTypeServiceImpl extends ServiceImpl<ScmDictTypeMapper, ScmDictType> implements ScmDictTypeService {
    @Resource
    private ScmDictTypeMapper scmDictTypeMapper;
    @Cacheable(value = "commonCache",key = "'scmdictType'")
    public List<ScmDictType> list(){
        return super.list();
    }
}
