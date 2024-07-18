package com.project.k6.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.k6.domain.Product;
import com.project.k6.persistence.ProductRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {
	
	@Autowired //컨테이너에 있는 객체와 연결시켜주는 역할.(동일화)ㅓ
	private ProductRepository productRepo;
	
//	밑에 new Product 안쓰려고 썼는데 오류남 = product
//	@Autowired
//	private Product product;
	
//	public void saveImage(MultipartFile img) throws IOException{
//		Product product = new Product();
//		product.setImg(img.getBytes());
//		product.setName("test" + img.getOriginalFilename());
//		product.setCatagory("clothes");
//		productRepo.save(product);
//	}
	
	//모든 product를 보여줌
	public List<Product> allimg() throws IOException{
		return productRepo.findAll();
	}
	
	//ID가 1번인 product에 대한 5가지 product를 추천해주는 코드
	public List<Product> recomendimg() {
		Optional<Product> productOptional = productRepo.findById(1l);
		Product product = productOptional.orElseThrow();
		List<Product> result = productRepo.findRandomProducts();
		result.add(product);
        return result;
	}
    @PostConstruct
    public List<Product> getProducts() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File("src/main/resources/sorted_상품코드_수량.json");
            List<Product> products = objectMapper.readValue(jsonFile, new TypeReference<List<Product>>(){});
            productRepo.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
    }
}
