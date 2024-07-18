package com.project.k6.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.k6.domain.Log;
import com.project.k6.dto.LogDTO;
import com.project.k6.service.LogService;

@RestController
public class LogController {

	
	@Autowired
	private LogService logService;
	
	//log 조회
	@GetMapping("/log")
	public Optional<Log> log(@RequestParam Long memberId) {
		return logService.log(memberId);
	}
	
	//log DB에 저장
	@PostMapping("/log")
    public ResponseEntity<Log> addlog(@RequestParam LogDTO logDTO) {
        Log log = logService.addlog(logDTO);
        return ResponseEntity.ok(log);
    }
}