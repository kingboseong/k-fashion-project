package com.project.k6.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.k6.domain.Product;
import com.project.k6.persistence.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	public void saveImage(MultipartFile img) throws IOException{
		Product product = new Product();
		product.setImg(img.getBytes());
		product.setName("test" + img.getOriginalFilename());
		product.setCatagory("clothes");
		productRepo.save(product);
	}
}
