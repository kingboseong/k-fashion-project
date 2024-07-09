package com.project.k6.domain;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "member")
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	@Id
	private String email;
	private String password;
	private String username;
	private Date date;
	private Role role;
	@OneToMany(mappedBy = "member") // 주인테이블, 양방향 mappedBy
	private Set<Log> log;
	
}
