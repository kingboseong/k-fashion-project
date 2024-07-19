package com.project.k6.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.k6.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p WHERE SUBSTRING(p.productCode, 5, 2) = :code")
    List<Product> findByProductCodeSegment(@Param("code") String code);
}
