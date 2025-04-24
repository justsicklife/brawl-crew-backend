package com.gamecrew.demo.service;

import com.gamecrew.demo.domain.Post;
import com.gamecrew.demo.dto.api.PostDTO;
import com.gamecrew.demo.mapper.PostMapper;
import com.gamecrew.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public List<PostDTO> getPostsWithUsers(int page, int size) {
        return postMapper.findPostsWithUsers(page, size);
    }

}
