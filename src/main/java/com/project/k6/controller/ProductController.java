package com.project.k6.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.k6.domain.Product;
import com.project.k6.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//사진 DB에 저장
	@PostMapping("/product")
	public String productimg(@RequestParam("file") MultipartFile file, Model model) {
		try {
			productService.saveImage(file);
			model.addAttribute("message", "File uploaded successfully!");
		}catch(IOException e) {
			model.addAttribute("message", "File upload failed!");
			e.printStackTrace();
		}
		return "success";
	}
	
	@GetMapping("/product")
	public List<Product> allimg() throws IOException {
		return productService.allimg();
		
	}
	
	@GetMapping("/product/recomend")
	public List<Product> recomendimg() throws IOException {
		return productService.recomendimg();
	}
}
