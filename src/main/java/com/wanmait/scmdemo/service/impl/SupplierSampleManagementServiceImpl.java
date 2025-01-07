package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.wanmait.scmdemo.entity.SupplierSampleManagement;
import com.wanmait.scmdemo.mapper.SupplierSampleManagementMapper;
import com.wanmait.scmdemo.service.SupplierSampleManagementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 供应商提交的的样品管理 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SupplierSampleManagementServiceImpl extends ServiceImpl<SupplierSampleManagementMapper, SupplierSampleManagement> implements SupplierSampleManagementService {
@Resource
private  SupplierSampleManagementMapper supplierSampleManagementMapper;
    public List<SupplierSampleManagement> selectSupplierSampleManagement(SupplierSampleManagement supplierSampleManagement) {
        LambdaQueryWrapper<SupplierSampleManagement> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SupplierSampleManagement::getSupplierId,supplierSampleManagement.getSupplierId());
        queryWrapper.eq(SupplierSampleManagement::getMailingCode,supplierSampleManagement.getMailingCode());
        return supplierSampleManagementMapper.selectList(queryWrapper);
    }


}
