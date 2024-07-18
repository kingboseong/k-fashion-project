package com.project.k6.service;

import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.k6.domain.Member;
import com.project.k6.dto.MemberDTO;
import com.project.k6.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("--------------------------loadUserByUsername-------------------------");
		
		Member member = memberRepository.getWithRoles(username);
		
		if(member ==null) {
			throw new UsernameNotFoundException("Not Fount");
		}
		
		MemberDTO memberDTO = new MemberDTO(
				member.getSeq(),
				member.getEmail(),
				member.getPassword(),
				member.getNickname(),
				member.getMemberRoleList().stream()
				.map(memberRole -> memberRole.name()).collect(Collectors.toList())
				);
		log.info(memberDTO);
			
		return memberDTO;
	}

}
