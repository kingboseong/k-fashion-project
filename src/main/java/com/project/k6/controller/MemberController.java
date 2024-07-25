package com.project.k6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.k6.domain.Member;
import com.project.k6.service.MemberService;


@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/api/member/signup")
	public String signup(@RequestBody Member member) {
		return memberService.signup(member);
	}
	
	@DeleteMapping("/api/member/delete")
	public String delete(@RequestParam String email) {
		return memberService.delete(email);
	}
}
