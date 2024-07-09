package com.project.k6.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "product")
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	private int id;
	private String catagory;
	private String name;
	
	@Lob
	private byte[] img;
	
}
