package com.project.k6.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	 
	//front랑 연결시켜주는 코드
	//이 클래스는 Spring boot 어플리케이션에서 CORS 설정을 전역적으로 적용.
	//이를 통해 욉 도메인에서 오는 요청을 허용하거나 제한 할 수 있음.
	//특히 프론트엔드 어플리케이션과의 통신을 원활하게 하기 위해 사용됨.
	//설정 항목은 모든 경로에 대해 모든 도메인과의 요청을 허용하고, 특정 HTTP메서드와 헤더를 허용하며, 자격 증명을 포함한 요청을 허용한다.
	//CORS(Cross-Origin Resource Sharing) = 다른 도메인에서 오는 요청을 허용하거나 제한할 수 있습니다.
    @Override 
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 CORS 설정을 적용
                .allowedOriginPatterns("*") // 모든 도메인에서 오는 요청을 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //괄호안에 있는 HTTP메서드를 허용한다.
                .allowedHeaders("*") // 모든 HTTP헤더를 허용한다.
                .allowCredentials(true); // 자격 증명을 허용한다.(쿠키, 인증 헤더 등)
    }
    
    //이미지 경로 front에 띄우는 코드
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    	registry.addResourceHandler("/images/**") // "/image/**" 경로로 들어오는 요청을 처리할 리로스 핸들러 추가
    			.addResourceLocations("file:///C:/spring/images/"); //리소스 핸들러가 제공할 실제 파일 시스템 경로를 지정
    	
    }

}