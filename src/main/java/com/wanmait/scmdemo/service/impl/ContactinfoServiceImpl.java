package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanmait.scmdemo.entity.Contactinfo;
import com.wanmait.scmdemo.mapper.ContactinfoMapper;
import com.wanmait.scmdemo.service.ContactinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 供应商的联系人信息表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-12-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContactinfoServiceImpl extends ServiceImpl<ContactinfoMapper, Contactinfo> implements ContactinfoService {
    @Resource
    private ContactinfoMapper contactinfoMapper;

    @Override
    public AjaxResult findBySupplierId(int id) {
        LambdaQueryWrapper<Contactinfo> contactinfoLambdaQueryWrapper=new LambdaQueryWrapper<>();
        contactinfoLambdaQueryWrapper.eq(Contactinfo::getSupplierId,id);
        Contactinfo contactinfo = contactinfoMapper.selectOne(contactinfoLambdaQueryWrapper);
        return AjaxResult.success("查询联系人成功").put("contactinfo",contactinfo);
    }
}
