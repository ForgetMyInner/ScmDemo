package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanmait.scmdemo.util.AjaxResult;

/**
 * <p>
 * 管理员用户表 服务类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
public interface AdminService extends IService<Admin> {
    AjaxResult login(Admin admin);
}
