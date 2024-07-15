package com.project.k6.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.k6.domain.Member;
import com.project.k6.domain.Role;
import com.project.k6.persistence.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//회원가입 / login은 postman으로 성공
	public Member signup(Member member){
//		아이디 중복걸러주는 코드인데 오류가 남. 아마 SecurityUserDetailsService가 있어서 그런 듯?
//		if(memberRepo.findByEmail(member.getEmail()) != null) {
//			throw new RuntimeException("Username is already taken");			
//		}
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		member.setDate(new Date());
		member.setRole(Role.ROLE_USER);
		return memberRepo.save(member);
	}
}
