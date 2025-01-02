package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanmait.scmdemo.entity.Imgs;
import com.wanmait.scmdemo.mapper.ImgsMapper;
import com.wanmait.scmdemo.service.ImgsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private ImgsMapper imgsMapper;


    @Override
    public boolean saveBatch(List<Imgs> entityList) {
        return super.saveBatch(entityList);
    }

    @Override
    public boolean saveBatch(List<Imgs> entityList, int batchSize) {
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public boolean saveOrUpdateBatch(List<Imgs> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    @Override
    public boolean saveOrUpdateBatch(List<Imgs> entityList, int batchSize) {
        return super.saveOrUpdateBatch(entityList, batchSize);
    }
}
