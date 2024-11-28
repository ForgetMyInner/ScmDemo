package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.SupplierSampleManagement;
import com.wanmait.scmdemo.mapper.SupplierSampleManagementMapper;
import com.wanmait.scmdemo.service.SupplierSampleManagementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
