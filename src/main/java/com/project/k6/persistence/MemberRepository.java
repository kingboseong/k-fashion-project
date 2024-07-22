package com.project.k6.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.k6.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Optional<Member> findByEmail(String email);
	
	@EntityGraph(attributePaths = {"memberRoleList"})
	@Query("select m from Member m where m.email = :email")
	Member getWithRoles(@Param("email") String email);
}
