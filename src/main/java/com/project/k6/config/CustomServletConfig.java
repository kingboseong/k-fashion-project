package com.project.k6.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 이 클래스가 Spring 설정 클래스임을 나타냄
public class CustomServletConfig implements WebMvcConfigurer {
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		
		registry.addFormatter(new LocalDateFormatter());
	}
}
