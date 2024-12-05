package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wanmait.scmdemo.entity.Admin;
import com.wanmait.scmdemo.entity.LoginLog;
import com.wanmait.scmdemo.mapper.AdminMapper;
import com.wanmait.scmdemo.mapper.LoginLogMapper;
import com.wanmait.scmdemo.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanmait.scmdemo.util.AjaxResult;
import com.wanmait.scmdemo.util.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 管理员用户表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    private AdminMapper adminMapper;
    @Resource
    private LoginLogMapper loginLogMapper;
    @Value("${token.secret}")
    private String secret;
    @Value("${token.expireMinutes}")
    private int expireMinutes;

    @Override
    public AjaxResult login(Admin admin) {
        if(StringUtils.isBlank(admin.getUsername())){
            return AjaxResult.error(10001,"用户名不能为空");
        }
        if(StringUtils.isBlank(admin.getPassword())){
            return AjaxResult.error(10003,"密码不能为空");
        }

        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername,admin.getUsername());
        queryWrapper.eq(Admin::getPassword,admin.getPassword());
        Admin loginAdmin = adminMapper.selectOne(queryWrapper);
        if(loginAdmin==null){
            return AjaxResult.error(10004,"用户名或者密码错误");
        }
        //更新登录信息
        Admin updateAdmin = new Admin();
        updateAdmin.setId(loginAdmin.getId());
        updateAdmin.setLoginIp(admin.getLoginIp());
        updateAdmin.setLoginCount(loginAdmin.getLoginCount()+1);
        updateAdmin.setLoginTime(new Date());
        adminMapper.updateById(updateAdmin);
        //记录登录日志
        LoginLog log = new LoginLog();
        log.setAdminId(loginAdmin.getId());
        log.setIp(admin.getLoginIp());
        loginLogMapper.insert(log);

        Map<String,String> claims = new HashMap<>();
        claims.put("username",admin.getUsername());
        claims.put("name",loginAdmin.getName());
        //生成jwt token
        String token = JWTUtils.createToken(loginAdmin.getId()+"",expireMinutes,loginAdmin.getPassword(),claims);
        loginAdmin.setPassword(null).setSalt(null);
        return AjaxResult.success("登录成功").put("admin",loginAdmin).put("token",token);
    }
}

