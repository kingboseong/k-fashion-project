package com.project.k6.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.k6.domain.Member;
import com.project.k6.persistence.MemberRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//회원가입 / login은 postman으로 성공
	public String signup(Member member){
		if(memberRepo.findByEmail(member.getEmail()).isEmpty()) { //.isEmpty() Repository에서 받는 member가 optional이라서.
			
			//받은거에서 추가저장
			member.setPassword(passwordEncoder.encode(member.getPassword()));
			member.setDate(new Date());
			member.getMemberRoleList();
			memberRepo.save(member);
			return "ok";
		} else {
			log.error("중복되는 email이 있습니다");
			return "error";
		}
	}

	public String delete(String email) {
		Member member = memberRepo.findByEmail(email)
		      .orElseThrow(() -> new RuntimeException("Member does not exist"));
		    
		memberRepo.delete(member);
		return "Member Deleted successfully";
	}
}
