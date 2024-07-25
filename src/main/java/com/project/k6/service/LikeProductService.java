package com.project.k6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.k6.domain.LikeProduct;
import com.project.k6.persistence.LikeProductRepository;

@Service
public class LikeProductService {
	
//	@Autowired
//	private MemberRepository memberRepo;
	
	@Autowired
	private LikeProductRepository likeProductRepo;
	
	
	public List<LikeProduct> likelist(String email) {
//		Member member = memberRepo.findByEmail(email)
//	              .orElseThrow(() -> new RuntimeException("Member not found"));
		return likeProductRepo.findByEmail(email);
	}
	
	
	public LikeProduct addlike(LikeProduct likeProducts) {
		
	    if(likeProductRepo.existsByEmailAndProductName(likeProducts.getEmail(), likeProducts.getProductName())) {	    	  
	          throw new RuntimeException("LikeProduct already exists");
	      }	
	      
	      LikeProduct likeProduct1 = new LikeProduct();
	      likeProduct1.setEmail(likeProducts.getEmail());
	      likeProduct1.setProductName(likeProducts.getProductName());
	      likeProduct1.setProductLink(likeProducts.getProductLink());
	      likeProduct1.setImgURL(likeProducts.getImgURL());
	      likeProduct1.setPrice(likeProducts.getPrice());
	      likeProduct1.setBrand(likeProducts.getBrand());

	      return likeProductRepo.save(likeProduct1);
	  }

	public LikeProduct deletelike(String email, String productName) {
		if(!likeProductRepo.existsByEmailAndProductName(email, productName)){
			throw new RuntimeException("LikeProduct does not exist");
		}
	    // 존재하는 경우 LikeProduct 엔티티를 조회
	    LikeProduct likeProduct = likeProductRepo.findByEmailAndProductName(email, productName)
	            .orElseThrow(() -> new RuntimeException("LikeProduct not found"));

	    // 조회된 LikeProduct 엔티티를 삭제
	    likeProductRepo.delete(likeProduct);

	    // 삭제된 엔티티를 반환 (혹은 필요에 따라 다른 반환 처리)
	    return likeProduct;
	}

}
