package com.cepheus.sovcombank.post.mapper;

import com.cepheus.sovcombank.post.dto.PostDto;
import com.cepheus.sovcombank.post.dto.PostDtoRequest;
import com.cepheus.sovcombank.post.model.Post;

public class PostMapper {
    public static Post mapDtoToPost(PostDto postDto){
        return Post.builder()
                .description(postDto.getDescription())
                .timeStamp(postDto.getTimeStamp())
                .build();
    }
    public static PostDtoRequest mapPostToDto(Post post){
        return PostDtoRequest.builder()
                .description(post.getDescription())
                .timeStamp(post.getTimeStamp())
                .author(PostDtoRequest.User
                        .builder()
                        .name(post.getAuthor().getName())
                        .name(post.getAuthor().getEmail())
                        .build())
                .build();
    }
}
