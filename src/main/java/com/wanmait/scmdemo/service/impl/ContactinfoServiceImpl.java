package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.Contactinfo;
import com.wanmait.scmdemo.mapper.ContactinfoMapper;
import com.wanmait.scmdemo.service.ContactinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 供应商的联系人信息表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContactinfoServiceImpl extends ServiceImpl<ContactinfoMapper, Contactinfo> implements ContactinfoService {

}
