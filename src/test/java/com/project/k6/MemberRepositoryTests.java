package com.project.k6;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.k6.domain.MemberRole;
import com.project.k6.domain.Member;
import com.project.k6.persistence.MemberRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void testInsertMember() {
		for(int i=0;i<10;i++) {
			Member member = Member.builder()
					.email("user"+i+"@aaa.com")
					.password(passwordEncoder.encode("1111"))
					.date(new Date())
					.nickname("USER"+i)
					.build();
			member.addRole(MemberRole.USER);
			if(i>=5) {
				member.addRole(MemberRole.ADMIN);
			}
			memberRepository.save(member);
		}
	}
	
	@Test
	public void testRead() {
		String email = "user9@aaa.com";
		
		Member member = memberRepository.getWithRoles(email);
		log.info("-------------");
		log.info(member);
	}
}
