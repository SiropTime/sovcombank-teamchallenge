package com.cepheus.sovcombank.post.repository;

import com.cepheus.sovcombank.post.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p order by p.timeStamp")
    List<Post> findAllDecs(Pageable pageable);
}