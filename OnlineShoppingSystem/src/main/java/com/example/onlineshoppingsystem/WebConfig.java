/*package com.example.onlineshoppingsystem;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("*")  // 允许所有 HTTP 方法，包括 OPTIONS
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}*/
