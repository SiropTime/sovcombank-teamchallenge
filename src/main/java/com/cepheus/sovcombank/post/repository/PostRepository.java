package com.cepheus.sovcombank.post.repository;

import com.cepheus.sovcombank.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}