package com.project.k6.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.project.k6.security.filter.JWTCheckFilter;
import com.project.k6.security.handler.APILoginFailHandler;
import com.project.k6.security.handler.APILoginSuccessHandler;
import com.project.k6.security.handler.CustomAccessDeniedHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

//Spring 설정 클래스
@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableMethodSecurity
public class CustomSecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
    	log.info("----------------securityconfig");
    	
    	//cors 설정 = (Cross-Origin Resuorce Sharing = 도메인이 다른 서버끼리 리소스를 주고 받을 떄 보안을 위해 설정)
    	//A가 B서버의 API에 접근이 가능하도록 해줌.
    	http.cors(httpSecurityCorsConfigurer->{
    		httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
    	});
    	
    	//세션을 staneless로 설정 = 세션이 필요없는 JWT로그인 같은 곳에서 설정하겠다는 뜻.
    	http.sessionManagement(sessionConfig->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    	
    	http.csrf(config->config.disable());
    	
    	http.formLogin(config->{
    		config.loginPage("/api/member/login");//사용자 정의 로그인 페이지 설정
    		config.successHandler(new APILoginSuccessHandler()); //로그인 성공 핸튿러 설정
    		config.failureHandler(new APILoginFailHandler()); //로그인 실패 핸들러 설정
    	});
    
    	//JWT체크 필터 
    	http.addFilterBefore(new JWTCheckFilter(),UsernamePasswordAuthenticationFilter.class);
    	
    	//예외 처리 핸들러 설정
    	http.exceptionHandling(config -> {config.accessDeniedHandler(new CustomAccessDeniedHandler());});
    	
    	// 로그아웃 설정 추가
//        http.logout(logout -> {
//            logout.logoutUrl("/api/logout")  // 로그아웃 엔드포인트 설정
//                  .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()) // 로그아웃 성공 시 핸들러
//                  .invalidateHttpSession(true)  // 세션 무효화
//                  .deleteCookies("JSESSIONID")  // 세션 쿠키 삭제
//                  .clearAuthentication(true);  // 인증 정보 제거
//        });
//    	#토큰 저장 위치 변경
//    	로컬, 브라우저에 저장하고있으면 안날라감
//    	세션스토리지에 저장해야함.
//    	
    	return http.build();
	}
    
    //cors 설정 소스를 Bean으로 등록.
    @Bean
	public CorsConfigurationSource corsConfigurationSource() {
    	
    	//CORS 설정을 생성
    	CorsConfiguration configuration = new CorsConfiguration();
    	
    	configuration.setAllowedOriginPatterns(Arrays.asList("*")); //모든 출처 허용
    	configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE")); //허용할 HTTP 메서드 설정
    	configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type")); //허용할 HTTP 헤더 설정
    	configuration.setAllowCredentials(true); //자격 증명 허용
    	
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", configuration);
    	
    	return source;
	}
 
}
