package com.project.k6.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.k6.domain.Log;
import com.project.k6.domain.Member;
import com.project.k6.persistence.LogRepository;
import com.project.k6.persistence.MemberRepository;

@Service
public class LogService {

	@Autowired
	private LogRepository logRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	public Optional<Log> log(String memberEmail) {
	    Member member = memberRepo.findByEmail(memberEmail)
	            .orElseThrow(() -> new RuntimeException("Member not found"));
	    
		Log log = new Log();
		log.setMember(member);
		logRepo.save(log);
		
		return logRepo.findByMember(member);
	}
}
