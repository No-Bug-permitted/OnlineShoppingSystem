package com.example.onlineshoppingsystem.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // 允许所有域名进行跨域调用
        config.addAllowedOriginPattern("*");  // 允许所有来源的跨域请求

        // 允许跨越发送cookie
        config.setAllowCredentials(true);  // 允许发送凭证（如 Cookie）

        // 放行全部原始头信息
        config.addAllowedHeader("*");  // 允许所有请求头

        // 允许所有请求方法跨域调用
        config.addAllowedMethod("*");  // 允许所有 HTTP 方法 (GET, POST, PUT, DELETE, 等)

        // 配置 URL 路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);  // 配置全局跨域规则，所有路径都允许跨域

        return new CorsFilter(source);  // 返回 CorsFilter 作为 Spring Bean
    }
}

