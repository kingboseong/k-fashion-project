package com.project.k6.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.k6.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
