package com.project.k6.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.k6.domain.Log;
import com.project.k6.service.LogService;

@RestController
public class LogController {

	
	@Autowired
	private LogService logService;
	
	//log 조회
	@PostMapping("/log")
	public Optional<Log> log(@RequestBody String memberEmail) {
		return logService.log(memberEmail);
	}
}
