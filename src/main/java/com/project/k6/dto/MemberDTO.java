package com.project.k6.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class MemberDTO extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nickname;
	private String email;
	private String password;
	private List<String> roleNames = new ArrayList<>();
	
	public MemberDTO(Long id, String email,String password, String nickname,List<String> roleNames) {
		super(email, password,roleNames.stream().map(str->new SimpleGrantedAuthority("Role_"+str)).collect(Collectors.toList()));
		this.id = id;
		this.email = email;
		this.password = password;
		this.roleNames = roleNames;
		this.nickname = nickname;
	}
	
	public Map<String, Object> getClaims(){
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("id", id);
		dataMap.put("email", email);
		dataMap.put("password",password );
		dataMap.put("nickname",nickname );
		dataMap.put("roleNames",roleNames );
		return dataMap;
	}
}
