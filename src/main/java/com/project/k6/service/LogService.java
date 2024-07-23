package com.project.k6.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
	
	public List<Log> log(Long memberId) {
	    Member member = memberRepo.findById(memberId)
	            .orElseThrow(() -> new RuntimeException("Member not found"));
		return logRepo.findByMember(member);
	}


	public Log addlog(LogDTO logDTO) {
		Member member = memberRepo.findByEmail(logDTO.getMemberId())
	            .orElseThrow(() -> new RuntimeException("Member not found"));
		
		Product inputProducts = productRepo.findById(logDTO.getInputproduct())
	            .orElseThrow(() -> new RuntimeException("Member not found"));
		
	    Set<Product> resultProducts = new HashSet<Product>();
	    for(Long inputProductId : logDTO.getResultproduct()) {
	    	Product product = productRepo.findById(inputProductId)
	    						.orElseThrow(() -> new RuntimeException("Product not found with ID: " + inputProductId));
	    	resultProducts.add(product);
	    }
		
	    Log log = new Log();
	    log.setMember(member);
	    log.setEvaluation(logDTO.isEvaluation());
	    log.setDate(new Date());
	    log.setInputproduct(inputProducts);
	    log.setResultproduct(resultProducts);

	    return logRepo.save(log);
	}
}
