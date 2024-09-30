package com.example.storycraft.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 정적 리소스 경로 설정
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/static/resources/");

        // 프로필 이미지 업로드 경로 설정
        registry.addResourceHandler("/profile-images/**")
                .addResourceLocations("file:///C:/embeded/upload/"); // Windows 경로 형식 수정

        // 스토리 이미지 업로드 경로 설정 추가
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///C:/embeded/upload/"); // 스토리 이미지 매핑 추가
    }
    
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/jsp/", ".jsp");
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("https://86a8-123-142-55-115.ngrok-free.app") // 클라이언트 도메인으로 변경
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true); // 쿠키 허용
    }
}
