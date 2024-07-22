package com.project.k6.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.k6.util.CustomJWTException;
import com.project.k6.util.JWTUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

//JWT를 사용하여 인증 토큰을 갱신하는 기능 제공
//기존에 만료된, 만료될 예정인 액세슽 토큰을 새로운 토큰으로 갱신할 수 있도록 해줌.
@RestController
@RequiredArgsConstructor
@Log4j2
public class APIRefreshController {
	
	@RequestMapping("api/member/refresh")
	public Map<String, Object> refresh(@RequestHeader("Authorization") String authHeader, String refreshToken){

		if(refreshToken == null) {
			throw new CustomJWTException("NULL_REFRASH");
		}

		if(authHeader == null || authHeader.length() < 7 ) {
			throw new CustomJWTException("INVALID_STRING");
		}

		String accessToken = authHeader.substring(7);

		//Access 토큰이 만료되지 않았다면
		if(checkExpiredToken(accessToken) == false) {
			return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
		}

		//Refresh 토큰 검증
		Map<String, Object> claims = JWTUtil.validateToken(refreshToken);

		log.info("refresh ... claims: " + claims);

		String newAccessToken = JWTUtil.generateToken(claims, 10);

		String newRefreshToken = checkTime((Integer)claims.get("exp")) == true ? JWTUtil.generateToken(claims, 60*24) : refreshToken;

		return Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken);
	}

	//시간이 1시간 미만으로 남았다면
	private boolean checkTime(Integer exp) {

		//JWT exp를 날짜로 변환
		java.util.Date expDate = new java.util.Date( (long)exp * (1000 ));

		//현재 시간과의 차이 계산 - 밀리세컨즈
		long gap = expDate.getTime() - System.currentTimeMillis();

		//분단위 계산
		long leftMin = gap / (1000 * 60);

		//1시간도 안남았는지..
		return leftMin < 60;
	}

	private boolean checkExpiredToken(String token) {

		try{
			JWTUtil.validateToken(token);
		}catch(CustomJWTException ex) {
			if(ex.getMessage().equals("Expired")) {
				return true;
			}
		}
		return false;
	}
}
