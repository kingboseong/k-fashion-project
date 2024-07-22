package com.project.k6.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.k6.domain.Like;
import com.project.k6.domain.Member;
import com.project.k6.domain.Product;

public interface LikeRepository extends JpaRepository<Like, Long> {
	List<Like> findByMember(Member member);

	boolean existsByMemberAndProduct(Member member, Product product);
}
