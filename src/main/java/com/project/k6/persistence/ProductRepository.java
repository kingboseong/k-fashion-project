package com.project.k6.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.k6.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findTop50ByOrderByQuantityDesc();
	
	@Query("SELECT p FROM Product p WHERE category in :code ORDER BY quantity DESC LIMIT 50")
    List<Product> getCategoryData(@Param("code") List<String> code);
}
