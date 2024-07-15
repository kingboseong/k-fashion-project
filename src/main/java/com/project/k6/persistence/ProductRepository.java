package com.project.k6.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.k6.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	 @Query(value = "SELECT * FROM product ORDER BY RAND() LIMIT 5", nativeQuery = true) //모델 나오기 전 테스트용 랜덤 5개
	 List<Product> findRandomProducts();
}
