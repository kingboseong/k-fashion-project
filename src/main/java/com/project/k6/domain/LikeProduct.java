package com.project.k6.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "likeproduct")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeProduct {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String email;
	private String productName;
	private String productLink;
	private String imgURL;
	private String price;
	private String brand;
	
//	@ManyToOne //양방향
//	@JoinColumn(name = "member_id",referencedColumnName = "seq", nullable = false)
//	private Member member;

}
