package com.project.k6.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.k6.domain.LikeProduct;

public interface LikeProductRepository extends JpaRepository<LikeProduct, Long> {
	boolean existsByEmailAndProductName(String email, String productName);
	Optional<LikeProduct> findByEmailAndProductName(String email, String productName);
	List<LikeProduct> findByEmail(String email);
}
