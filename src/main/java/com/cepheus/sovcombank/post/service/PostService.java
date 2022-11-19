package com.cepheus.sovcombank.post.service;

import com.cepheus.sovcombank.post.model.Post;

import java.util.List;

public interface PostService {
    void add(Post post,String emailUser);
    Post findById(Long id);
    List<Post> findAll(int from,int size);
    void deleteById(Long id);

}
