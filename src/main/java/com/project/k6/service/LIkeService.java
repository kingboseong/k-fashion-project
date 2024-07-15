package com.project.k6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.k6.domain.Like;
import com.project.k6.domain.Member;
import com.project.k6.domain.Product;
import com.project.k6.persistence.LikeRepository;
import com.project.k6.persistence.MemberRepository;
import com.project.k6.persistence.ProductRepository;

@Service
public class LikeService {

	@Autowired
	private LikeRepository likeRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	public List<Like> like() {
		return likeRepo.findAll();
	}
	
	public Like addlike(String memberId, Long productId) {
	      Member member = memberRepo.findByEmail(memberId)
	              .orElseThrow(() -> new RuntimeException("Member not found"));
	      Product product = productRepo.findById(productId)
	              .orElseThrow(() -> new RuntimeException("Product not found"));

	      Like like = new Like();
	      like.setMember(member);
	      like.setProduct(product);

	      return likeRepo.save(like);
	  }
}

