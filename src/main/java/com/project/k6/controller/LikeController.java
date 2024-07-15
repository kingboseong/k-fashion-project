package com.project.k6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	@PostMapping("/like")
	public List<Like> like(){
		return likeService.like();
	}
	
	//찜 DB에 저장
	@PostMapping("/likes")
    public ResponseEntity<Like> addlike(@RequestParam String memberId, @RequestParam Long productId) {
        Like like = likeService.addlike(memberId, productId);
        return ResponseEntity.ok(like);
    }
}
