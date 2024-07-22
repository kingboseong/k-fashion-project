package com.project.k6.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.k6.domain.Product;
import com.project.k6.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/api/products/list")
	public List<Product> products() throws IOException {
		return productService.products();	
	}
	
	@GetMapping("/api/products/list/{code}")
    public List<Product> findByProductCodeSegment(@PathVariable String code) {
        return productService.findByProductCodeSegment(code);
    }
}
