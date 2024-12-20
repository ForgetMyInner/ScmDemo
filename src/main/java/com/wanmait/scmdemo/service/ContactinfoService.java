package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.Contactinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanmait.scmdemo.util.AjaxResult;

/**
 * <p>
 * 供应商的联系人信息表 服务类
 * </p>
 *
 * @author wanmait
 * @since 2024-12-10
 */
public interface ContactinfoService extends IService<Contactinfo> {
    AjaxResult findBySupplierId(int id);

}
