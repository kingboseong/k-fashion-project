package com.project.k6.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@ToString(exclude="memberRoleList")
@Table(name = "member")
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	@Column(unique = true)
	private String email;
	private String password;
	private String nickname;
	private Date date;
	@ElementCollection(fetch=FetchType.EAGER)
	@Builder.Default
	private List<MemberRole> memberRoleList = new ArrayList<>();
	@OneToMany(mappedBy = "member") // 주인테이블, 양방향 mappedBy
	@JsonIgnore
	private Set<Log> log;
	
	public void addRole(MemberRole memberRole) {
		memberRoleList.add(memberRole);
	}
	
	public void clearRole() {
		memberRoleList.clear();
	}

}
