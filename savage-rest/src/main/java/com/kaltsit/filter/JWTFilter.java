package com.kaltsit.filter;

import com.alibaba.fastjson2.JSONObject;
import com.kaltsit.shiro.JWTToken;
import com.kaltsit.utils.CookieUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     * 是否允许访问，如果带有 token，则对 token 进行检查，否则直接通过
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            executeLogin(request, response);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // 认证失败时，清除token
            CookieUtils.delCookieByName(response, "token");
            return false;
        }
    }

    /**
     * 无token或false时，会进入这个方法
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //这里是个坑，如果不设置的接受的访问源，那么前端都会报跨域错误，因为这里还没到corsConfig里面
        httpServletResponse.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        JSONObject json = new JSONObject();
        json.put("code", 401);
        json.put("data", null);
        json.put("msg", "token认证失效，重新登陆");
        httpServletResponse.getWriter().write(json.toString());
        httpServletResponse.getWriter().flush();
        return false;
    }

    /**
     * 判断用户是否想要登入。
     * 检测 header 里面是否包含 Token 字段
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        return token != null;
    }

    /**
     * executeLogin实际上就是先调用createToken来获取token，这里我们重写了这个方法，就不会自动去调用createToken来获取token
     * 然后调用getSubject方法来获取当前用户再调用login方法来实现登录
     * 这也解释了我们为什么要自定义jwtToken，因为我们不再使用Shiro默认的UsernamePasswordToken了。
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        JWTToken jwt = new JWTToken(token);
        //交给自定义的realm对象去登录，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwt);
        return true;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        //update-begin-author:scott date:20200907 for:issues/I1TAAP 前后端分离，shiro过滤器配置引起的跨域问题
        // 是否允许发送Cookie，默认Cookie不包括在CORS请求之中。设为true时，表示服务器允许Cookie包含在请求中。
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        //update-end-author:scott date:20200907 for:issues/I1TAAP 前后端分离，shiro过滤器配置引起的跨域问题
        return super.preHandle(request, response);
    }



}