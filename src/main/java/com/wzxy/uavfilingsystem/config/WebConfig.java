package com.wzxy.uavfilingsystem.config;

import com.wzxy.uavfilingsystem.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//使用@Configuration注解和代码，替代xml文件进行配置
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * (新增代码)添加jwt拦截器,并指定拦截路径
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/users/**")
                .addPathPatterns("/userprofiles/**")
                .addPathPatterns("/drones/**")
                .addPathPatterns("/dronetypes/**")
                .addPathPatterns("/manufacturers/**")
                .addPathPatterns("/droneflightareas/**")
//不需要被校验url: 查询所有一级栏目及其二级栏目的接口(供前台使用)
                .excludePathPatterns("/login",
                        "/register");
    }
    /**
     * (新增代码)创建jwt拦截器对象并加入spring容器
     */
    @Bean
    public JwtInterceptor jwtInterceptor() {return new JwtInterceptor();
    }
    // (原有代码)跨域配置: 通过跨域过滤器实现
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
// 允许跨域的头部信息
        config.addAllowedHeader("*");
// 允许跨域的方法
        config.addAllowedMethod("*");
// 可访问的外部域
        config.addAllowedOrigin("*");
// 需要跨域用户凭证（cookie、HTTP认证及客户端SSL证明等）
//config.setAllowCredentials(true);
//config.addAllowedOriginPattern("*");
// 跨域路径配置
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}


