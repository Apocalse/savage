package com.kaltsit.ascept;

import com.alibaba.fastjson2.JSONObject;
import com.kaltsit.annotation.SysLog;
import com.kaltsit.commons.SysLogType;
import com.kaltsit.entity.sys.SysLogEntity;
import com.kaltsit.entity.sys.SysUserEntity;
import com.kaltsit.service.sys.impl.SysLogServiceImpl;
import com.kaltsit.service.sys.impl.SysUserServiceImpl;
import com.kaltsit.utils.HttpContextUtils;
import com.kaltsit.utils.IPUtils;
import com.kaltsit.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * 系统日志，切面处理类
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    @Resource
    private SysLogServiceImpl sysLogService;
    @Resource
    private SysUserServiceImpl userService;
    @Value ("${server.servlet.context-path:#{''}}")
    private String contextPath;
    // 记录项目路径长度
    private static int serverContextPathLength = -1;

    @Pointcut("@annotation(com.kaltsit.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        saveSysLog(point, time);
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogEntity logEntity = new SysLogEntity();
        //用户名
        logEntity.setUsername(UserUtils.getCurrentUserName());
        String currentUserId = UserUtils.getCurrentUserId();
        logEntity.setCreateUserId(currentUserId);
        SysLog logAnnotation = method.getAnnotation(SysLog.class);
        //注解上的描述
        logEntity.setOperation(logAnnotation.value());
        logEntity.setType(logAnnotation.type());
        //请求的方法名
        logEntity.setMethod(signature.getName());
        //系统登录的时候参数密码参数不存储
        Object[] args = joinPoint.getArgs();
        if (logAnnotation.type() == SysLogType.LOGIN) {
            if (args.length == 1) {
                if (SysUserEntity.class.getName().equals(args[0].getClass().getName())) {
                    try {
                        SysUserEntity userEntity = (SysUserEntity) args[0];
                        SysUserEntity newLoginForm = new SysUserEntity();
                        BeanUtils.copyProperties(userEntity, newLoginForm);
                        // 避免密码泄露
                        newLoginForm.setPassword("******");
                        String params = JSONObject.toJSONString(newLoginForm);
                        logEntity.setParams(params);
                        // 登录的用户
                        logEntity.setUsername(userEntity.getUsername());
                        currentUserId = userService.getIdByUsername(userEntity.getUsername());
                        logEntity.setCreateUserId(currentUserId);
                    } catch (Exception e) {
                        log.error(ExceptionUtils.getStackTrace(e));
                    }
                }
            }
        } else {
            try {
                String params = JSONObject.toJSONString(args);
                logEntity.setParams(params);
            } catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }
        log.info("当前登录用户:{}", currentUserId);
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        logEntity.setIp(IPUtils.getIpAddress(request));
        //获取请求的地址
        String requestURI = request.getRequestURI();
        // 去除项目前缀
        if (StringUtils.isNotBlank(requestURI)) {
            if (serverContextPathLength == -1) {
                serverContextPathLength = contextPath.length();
            }
            int length = requestURI.length();
            if (length > serverContextPathLength) {
                requestURI = requestURI.substring(serverContextPathLength);
            }
            length = requestURI.length();
            if (length > 250) {
                requestURI = requestURI.substring(0, 250);
            }
        }
        logEntity.setRequestUri(requestURI);
        logEntity.setTime(time);
        logEntity.setCreateDate(new Date());
        //保存系统日志
        sysLogService.save(logEntity);
    }
}
