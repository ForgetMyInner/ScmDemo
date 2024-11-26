package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.ScmDictData;
import com.wanmait.scmdemo.mapper.ScmDictDataMapper;
import com.wanmait.scmdemo.service.ScmDictDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
