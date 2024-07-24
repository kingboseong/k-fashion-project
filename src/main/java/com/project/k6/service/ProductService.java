package com.project.k6.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.k6.domain.Product;
import com.project.k6.persistence.ProductRepository;

@Service
public class ProductService {
	
	@Autowired //컨테이너에 있는 객체와 연결시켜주는 역할.(동일화)
	private ProductRepository productRepo;


	//product 50를 보여줌
	public List<Product> products() throws IOException{
		return productRepo.findTop50ByOrderByQuantityDesc();
	}
	
	//해당 category product만 보여줌
	public List<Product> findByProductCodeSegment(String code) {
		List<String> cateList = new ArrayList<>();
		
		switch (code) {
		case "상의" : cateList.addAll(Arrays.asList("TS", "TN", "KT", "KN", "BL", "WS", "BN"));

		break;
					
		case "하의" : cateList.addAll(Arrays.asList("PT", "DP", "SK", "LG"));
		break;
		
		case "아우터" : cateList.addAll(Arrays.asList("JP", "JK", "CT", "VT", "CA", "CD", "FU", "OU"));
			
		break;
		
		case "원피스" : cateList.add("OP");
		break;
		
		case "세트" : cateList.add("ST");
		break;

		default:
			break;
		}
		
		List<Product> plist = productRepo.getCategoryData(cateList);
		
//		for(int i=0;i<plist.size();i++) {
//			plist.get(i).setImagePath(plist.get(i).getImagePath().replace("\\", "/"));
//		}
		
		//현직에서 for문 보다 stream 선호
		List<Product> updatedList = plist.stream()
	            .map(product -> {
	                product.setImagePath(product.getImagePath().replace("\\","/"));
	                return product;
	            })
	            .collect(Collectors.toList());

	        // 이제 updatedList는 imagePath가 설정된 제품 목록입니다.
	        // updatedList를 사용하거나 plist를 재할당하여 사용할 수 있습니다.
	        plist = updatedList;
	    return plist;
	}
	
}
