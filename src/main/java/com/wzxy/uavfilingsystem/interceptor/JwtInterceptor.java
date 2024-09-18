package com.wzxy.uavfilingsystem.interceptor;

import com.wzxy.uavfilingsystem.common.ResultCode;
import com.wzxy.uavfilingsystem.exception.ServiceException;
import com.wzxy.uavfilingsystem.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.method.HandlerMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        // 从 HTTP请求头中取出 token
        String token = httpServletRequest.getHeader("Authorization");
        if (token.startsWith("Bearer")) {
            token = token.substring(7);
        }
        System.out.println("::"+token);
        if (token == null) {
            throw new RuntimeException("无token，请重新登录");
        }
        // 验证 token
        try {
        //解析JWT
            Claims claims = JwtUtil.parseJWT(token);
            String username = String.valueOf(claims.get("username"));
            httpServletRequest.setAttribute("username",username);
        }catch (ExpiredJwtException e){
        //登录到期
            throw new RuntimeException("登录到期");
        }catch (MalformedJwtException e){
        //令牌失效
            throw new RuntimeException("令牌失效");
        }catch (Exception e){

            e.printStackTrace();
//服务器内部错误
            throw new
                    ServiceException(ResultCode.SYSTEM_INNER_ERROR);
        }
        return true;
    }
}
