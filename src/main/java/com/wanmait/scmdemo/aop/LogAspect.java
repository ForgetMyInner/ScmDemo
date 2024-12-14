package com.wanmait.scmdemo.aop;

import com.wanmait.scmdemo.entity.Admin;
import com.wanmait.scmdemo.entity.Log;
import com.wanmait.scmdemo.service.LogService;
import com.wanmait.scmdemo.util.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Aspect
public class LogAspect {
    @Resource
    private LogService logService;

    @Pointcut("@annotation(com.wanmait.scmdemo.aop.LogInfo)")
    public void pointcutExpression(){}
    @AfterReturning(value = "pointcutExpression()",returning = "returnValue")
    public void addLog(JoinPoint joinPoint, Object returnValue){
        //获得HttpServletRequest
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Admin admin = (Admin)request.getAttribute("admin");
        if(admin==null){
            return;
        }
        Log log = new Log();
        log.setAdminId(admin.getId());
        log.setIp(IPUtils.getClientIpAddr(request));
        StringBuilder info = new StringBuilder();
        info.append("目标类：").append(joinPoint.getTarget().getClass().getName()).append(";");
        info.append("目标方法：").append(joinPoint.getSignature().getName()).append(";");
        info.append("参数：").append(Arrays.toString(joinPoint.getArgs())).append(";");
        if(returnValue!=null){
            info.append("返回值：").append(returnValue).append(";");
        }
        //获得加了@LogInfo注解的方法
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LogInfo logInfo = method.getAnnotation(LogInfo.class);

        log.setInfo(logInfo.value());
        logService.save(log);
    }


}
