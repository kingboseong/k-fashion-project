package com.project.k6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.k6.domain.LikeProduct;
import com.project.k6.service.LikeProductService;

@RestController
public class LikeProductController {
	
	@Autowired
	private LikeProductService likeProductService;
	
	//찜 조회
	@GetMapping("/getLikeProductList")
	public ResponseEntity<List<LikeProduct>> likelist(String email) {
        List<LikeProduct> likeProducts = likeProductService.likelist(email);
        return ResponseEntity.ok(likeProducts);
    }
	
	//상품 찜하기
	@PostMapping("/likeproduct")
	public ResponseEntity<LikeProduct> addlike(@RequestBody LikeProduct likeProduct) {
        LikeProduct likeProducts = likeProductService.addlike(likeProduct);
        return ResponseEntity.ok(likeProducts);
    }
	
	//찜한 상품 삭제하기
	//RequestBody가 안되면 RequestParam으로 바꿔서 해보기.
	@DeleteMapping("/likecancel")
	public ResponseEntity<LikeProduct> deletelike(@RequestParam String email, @RequestParam String productName) {
        LikeProduct likeProducts = likeProductService.deletelike(email, productName);
        return ResponseEntity.ok(likeProducts);
    }
}
