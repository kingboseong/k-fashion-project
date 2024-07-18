package com.project.k6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.k6.domain.Member;
import com.project.k6.service.MemberService;


@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/api/member/signup")
	public Member signup(@RequestBody Member member) {
		return memberService.signup(member);
	}
}
