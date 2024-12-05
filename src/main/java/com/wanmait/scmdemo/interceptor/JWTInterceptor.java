package com.wanmait.scmdemo.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.wanmait.scmdemo.entity.Admin;
import com.wanmait.scmdemo.service.AdminService;
import com.wanmait.scmdemo.util.JWTUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Resource
    private AdminService adminService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是预检请求放行
        if("OPTIONS".equals(request.getMethod()))
        {
            return true;
        }

        //从请求头中获得token
        String token = request.getHeader("token");
        if(token==null){
/*            PrintWriter out = response.getWriter();
            AjaxResult ajaxResult = AjaxResult.error(10002,"token没有传递");
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(ajaxResult);
            out.print(json);
            return false;
            */
            throw new JWTVerificationException("请求头中没有携带token");
        }
        //验证token
//        Integer adminId = null;
//        try {
//            adminId = Integer.parseInt(JWTUtils.getAudience(token));
//        } catch (NumberFormatException e) {
//            throw new JWTVerificationException("token信息不正确");
//        }
//        Admin admin = adminService.getById(adminId);
        Admin admin = (Admin)redisTemplate.opsForValue().get("token:"+token);
        if(admin==null){
            throw new JWTVerificationException("token信息不正确,没有查询到对应的用户");
        }
        JWTUtils.verifyToken(token,admin.getPassword());
        request.setAttribute("admin",admin);
        return true;
    }
}