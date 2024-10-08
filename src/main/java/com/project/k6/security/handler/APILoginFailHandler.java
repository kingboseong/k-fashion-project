package com.project.k6.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

//Spring Security에서 인증 실패를 처리하는 핸들러 클래스
@Log4j2
public class APILoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		log.info("Login fail...."+exception); //로그인 실패 메시지 로깅
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(Map.of("error","ERROR_LOGIN"));
		
		response.setContentType("application/json");
		
		PrintWriter printWriter = response.getWriter();
		printWriter.println(jsonStr);
		printWriter.close();
	}

}
