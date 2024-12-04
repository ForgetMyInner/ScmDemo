package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanmait.scmdemo.entity.ScmDictData;
import com.wanmait.scmdemo.mapper.ScmDictDataMapper;
import com.wanmait.scmdemo.service.ScmDictDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ScmDictDataServiceImpl extends ServiceImpl<ScmDictDataMapper, ScmDictData> implements ScmDictDataService {
@Resource
private ScmDictDataMapper scmDictDataMapper;

    @Cacheable(value = "commonCache",key = "'dictType:'+#dictType")
    @Override
    public List<ScmDictData> list(String scmDictType) {
        LambdaQueryWrapper<ScmDictData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ScmDictData::getDictType,scmDictType);
        lambdaQueryWrapper.select(ScmDictData::getDictCode,ScmDictData::getDictType,ScmDictData::getDictValue,ScmDictData::getDictLabel);
        return scmDictDataMapper.selectList(lambdaQueryWrapper);
    }

}
