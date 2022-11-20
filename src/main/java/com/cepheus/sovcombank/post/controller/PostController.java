package com.cepheus.sovcombank.post.controller;

import com.cepheus.sovcombank.exception.ValidationException;
import com.cepheus.sovcombank.post.dto.PostDto;
import com.cepheus.sovcombank.post.dto.PostDtoRequest;
import com.cepheus.sovcombank.post.mapper.PostMapper;
import com.cepheus.sovcombank.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/posts")
@Slf4j
public class PostController {
    private final PostService postService;

    @PostMapping
    public HttpStatus add(@RequestBody PostDto postDto) {
        postService.add(PostMapper.mapDtoToPost(postDto), postDto.getUserEmail());
        return HttpStatus.OK;
    }

    @GetMapping
    public List<PostDtoRequest> findAll(@RequestParam(defaultValue = "1") Integer from,
                                        @RequestParam(defaultValue = "10") Integer size) {
        if (from < 0 || size == 0) {
            throw new ValidationException("Incorrect request parameters were passed: start from the page " + from +
                    " ,number of elements on the page " + size);
        }

        return postService.findAll(from, size).stream().map(PostMapper::mapPostToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PostDtoRequest findById(@PathVariable Long id) {
        return PostMapper.mapPostToDto(postService.findById(id));
    }

}
