package com.project.k6.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.k6.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	Optional<Member> findByEmail(String email);
}
