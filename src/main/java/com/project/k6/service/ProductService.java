package com.project.k6.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.k6.domain.Product;
import com.project.k6.persistence.ProductRepository;

@Service
public class ProductService {
	
	@Autowired //컨테이너에 있는 객체와 연결시켜주는 역할.(동일화)ㅓ
	private ProductRepository productRepo;

	
	//모든 product를 보여줌
	public List<Product> products() throws IOException{
		return productRepo.findAll();
	}
	
	//해당 category product만 보여줌
	public List<Product> findByProductCodeSegment(String code) {
	    return productRepo.findByProductCodeSegment(code);
	}
	
}
