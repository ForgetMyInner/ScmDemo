package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.PurchaseApply;
import com.wanmait.scmdemo.mapper.PurchaseApplyMapper;
import com.wanmait.scmdemo.service.PurchaseApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 采购申请（还未购买） 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseApplyServiceImpl extends ServiceImpl<PurchaseApplyMapper, PurchaseApply> implements PurchaseApplyService {
    @Autowired
    private PurchaseApplyMapper purchaseApplyMapper;
    @Override
    public boolean save(PurchaseApply entity) {
        entity.setCode(generateCode());
        entity.setEnterTime(new Date());
        boolean save = super.save(entity);
        Integer id = entity.getId();
        if(id!=null) {

        }
        return save;
    }
    private String generateCode() {
        String purchaseApplyCode;
        do {
            purchaseApplyCode = String.valueOf(System.currentTimeMillis());
        } while (purchaseApplyMapper.exists(purchaseApplyCode));
        return purchaseApplyCode;
    }
}
