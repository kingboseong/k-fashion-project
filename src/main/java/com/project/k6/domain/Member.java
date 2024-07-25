package com.project.k6.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
//	cascade = CascadeType.ALL: 부모 엔티티(Member)에 대한 모든 연관 작업(저장, 삭제 등)을 자식 엔티티(Log)에도 적용합니다.
//	orphanRemoval = true: 부모 엔티티와의 연관 관계가 끊어진 자식 엔티티를 자동으로 삭제합니다.
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true) // 주인테이블, 양방향 mappedBy
	@JsonIgnore
	private Set<Log> log;
	
//	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Like> like;
	
//	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<LikeProduct> likeproduct;
	
//	@OneToMany //단방향
//	@JoinColumn(name = "likeproduct_seq") // _id는 빼도 되지만 이해를 돕기위해 넣는것을 권장함.
//	private Set<LikeProduct> likeproduct;
	
	public void addRole(MemberRole memberRole) {
		memberRoleList.add(memberRole);
	}
	
	public void clearRole() {
		memberRoleList.clear();
	}
}
