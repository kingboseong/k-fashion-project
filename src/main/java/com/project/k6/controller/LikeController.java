package com.project.k6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.k6.domain.Like;
import com.project.k6.service.LikeService;

@RestController
public class LikeController {

	@Autowired
	private LikeService likeService;
	
	//찜 조회
	@GetMapping("/like")
	public List<Like> like(@RequestParam Long memberId){
		return likeService.like(memberId);
	}
	
	//찜 DB에 저장
	@PostMapping("/like")
    public ResponseEntity<Like> addlike(@RequestParam Long memberId, @RequestParam Long productId) {
        Like like = likeService.addlike(memberId, productId);
        return ResponseEntity.ok(like);
    }
}
