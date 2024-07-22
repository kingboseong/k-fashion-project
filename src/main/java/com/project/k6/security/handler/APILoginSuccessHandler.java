package com.project.k6.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.google.gson.Gson;
import com.project.k6.util.JWTUtil;
import com.project.k6.dto.MemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

//Spring Security 인증 성공 처리 핸들러 클래스
@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException{
		log.info("-----------------------");
		log.info(authentication);
		log.info("-----------------------");
		
		MemberDTO memberDTO = (MemberDTO)authentication.getPrincipal();
		
		Map<String,Object> claims = memberDTO.getClaims();
		
		String accessToken = JWTUtil.generateToken(claims, 10);
		String refreshToken = JWTUtil.generateToken(claims, 60*24);
		
		claims.put("accessToken", accessToken);
		claims.put("refreshToken", refreshToken);
		
		Gson gson = new Gson();
		
		String jsonStr = gson.toJson(claims);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.println(jsonStr);
		printWriter.close();
	}
}