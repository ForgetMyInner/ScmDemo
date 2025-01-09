package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanmait.scmdemo.entity.SupplierSign;
import com.wanmait.scmdemo.mapper.SupplierSignMapper;
import com.wanmait.scmdemo.service.SupplierSignService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 供应商签约 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SupplierSignServiceImpl extends ServiceImpl<SupplierSignMapper, SupplierSign> implements SupplierSignService {
    @Resource
    private SupplierSignMapper supplierSignMapper;

    @Override
    public SupplierSign getSupplierById(Integer supplierId) {
        // 使用 LambdaQueryWrapper 构建查询条件
        LambdaQueryWrapper<SupplierSign> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SupplierSign::getSupplierId, supplierId);
        // 通过 Mapper 进行查询
        return supplierSignMapper.selectOne(queryWrapper);
    }
}
