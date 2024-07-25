//package com.project.k6.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.project.k6.domain.Member;
//import com.project.k6.persistence.LikeProductRepository;
//import com.project.k6.persistence.MemberRepository;
//
//@Service
//public class LikeService {
//
//	@Autowired
//	private LikeProductRepository likeProductRepo;
//	
//	@Autowired
//	private MemberRepository memberRepo;
////	
////	public String like(String email) {
////		Member member = memberRepo.findByEmail(email)
////	              .orElseThrow(() -> new RuntimeException("Member not found"));
////		return likeProductRepo.findByEmail(member);
////	}
//}