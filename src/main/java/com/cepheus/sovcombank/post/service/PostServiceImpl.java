package com.cepheus.sovcombank.post.service;

import com.cepheus.sovcombank.exception.NotFoundException;
import com.cepheus.sovcombank.exception.UnauthorizedException;
import com.cepheus.sovcombank.post.model.Post;
import com.cepheus.sovcombank.post.repository.PostRepository;
import com.cepheus.sovcombank.user.model.User;
import com.cepheus.sovcombank.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PostServiceImpl implements PostService {
    private final UserService userService;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public void add(Post post, String emailUser) {
        User user = userService.findByEmail(emailUser);
        if (!user.getApproved() || user.getBanned()) {
            throw new UnauthorizedException("The user is not affected or banned");
        }
        post.setAuthor(user);
        postRepository.save(post);
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пост с id: " + id + " не найден."));
    }

    @Override
    public List<Post> findAll(int from, int size) {
        return postRepository.findAllDecs(PageRequest.of(from / size, size));
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
