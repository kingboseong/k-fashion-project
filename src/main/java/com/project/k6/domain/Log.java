package com.project.k6.domain;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Builder
@Table(name = "log")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Log {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
	private boolean evaluation;
	private Date date;
	
	@ManyToOne //단방향
	@JoinColumn(name = "inputproduct_id") // _id는 빼도 되지만 이해를 돕기위해 넣는것을 권장함.
	private Product inputproduct;
	
	@ManyToOne //양방향
	@JoinColumn(name = "member_email")
	private Member member;
	
	@ManyToMany
	@JoinTable(
		name = "log_product", // jointable 의 이름
	    joinColumns = @JoinColumn(name = "log_seq"), //log 테이블(이쪽)의 id 를 필드값으로 넣겠다. 
	    inverseJoinColumns = @JoinColumn(name = "resultproduct_id") //hscode 테이블(저쪽)의 hscode 를 필드값으로 넣겠다.
	)
	private Set<Product> resultproduct; 
}
