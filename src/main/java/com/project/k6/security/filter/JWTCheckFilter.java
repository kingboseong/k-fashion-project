package com.project.k6.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.project.k6.util.JWTUtil;
import com.project.k6.dto.MemberDTO;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter{// OncePerRequestFilter를 확장하여 요청당 한 번만 필터를 실행하도록 설정
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{
		
		//Preflight요청은 체크하지 않음.
		if(request.getMethod().equals("OPTIONS")) {
			return true;
		}
		//요청 URI를 가져옴.
		String path = request.getRequestURI();
		
		log.info("check uri.............." + path);
		
		if(path.startsWith("/")) {
			return true;
		}

		//위 if문에서 설정한 api경로 외의 경로는 필터 적용
		return false;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("------------------------JWTCheckFilter........................");
		
		String authHeaderStr = request.getHeader("Authorization"); // Authorization 헤더에서 토큰을 가져옴
		
		try {
			//Bearer accestoken...
			String accessToken = authHeaderStr.substring(7);// "Bearer " 이후의 실제 토큰을 추출
			Map<String, Object> claims = JWTUtil.validateToken(accessToken);// JWT 토큰을 검증하고 클레임을 추출
			
			//클래임 정보 로깅.
			log.info("JWT claims: " + claims);
			
			//토큰에서 claim 정보 추출
			String email = (String) claims.get("email");
			String nickname = (String) claims.get("nickname");
			String password = (String) claims.get("password");
			Long id = (Long) claims.get("id");
			@SuppressWarnings("unchecked")
			List<String> roleNames = (List<String>) claims.get("roleNames");
			
			MemberDTO memberDTO = new MemberDTO(id,email, password, nickname, roleNames);
			
			log.info("-------------------------------------------------");
			log.info(memberDTO);
			log.info(memberDTO.getAuthorities());
			
			//인증 토큰 생성
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberDTO, password, memberDTO.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			
			filterChain.doFilter(request, response);
			
		}catch(Exception e){
			log.error("JWT Check Error..............");
			log.error(e.getMessage());
			
			Gson gson = new Gson();
			String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));
			
			response.setContentType("application/json");
			PrintWriter printWriter = response.getWriter();
			printWriter.println(msg);
			printWriter.close();
			
		}
	}
}
