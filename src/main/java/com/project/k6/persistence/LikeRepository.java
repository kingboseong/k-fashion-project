package com.project.k6.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.k6.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Integer> {

}
