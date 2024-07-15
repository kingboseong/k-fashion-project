package com.project.k6.service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.k6.domain.Log;
import com.project.k6.domain.Member;
import com.project.k6.domain.Product;
import com.project.k6.dto.LogDTO;
import com.project.k6.persistence.LogRepository;
import com.project.k6.persistence.MemberRepository;
import com.project.k6.persistence.ProductRepository;

@Service
public class LogService {

	@Autowired
	private LogRepository logRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	public Optional<Log> log(String memberEmail) {
	    Member member = memberRepo.findByEmail(memberEmail)
	            .orElseThrow(() -> new RuntimeException("Member not found"));
	    
		Log log = new Log();
		log.setMember(member);
		logRepo.save(log);
		
		return logRepo.findByMember(member);
	}

	

	public Log addlog(LogDTO logDTO) {
		Member member = memberRepo.findByEmail(logDTO.getMemberId())
	            .orElseThrow(() -> new RuntimeException("Member not found"));
		
	    Set<Product> resultProducts = logDTO.getResultproduct();
	    for(Product product : resultProducts) {
	    	productRepo.findById(product.getId())
	    		.orElseThrow(() -> new RuntimeException("Product not found with ID: " + product.getId()));
	    }
		
	    Log log = new Log();
	    log.setMember(member);
	    log.setEvaluation(logDTO.isEvaluation());
	    log.setDate(new Date());
	    log.setInputproduct(logDTO.getInputproduct());
	    log.setResultproduct(resultProducts);

	    return logRepo.save(log);
	}
}








