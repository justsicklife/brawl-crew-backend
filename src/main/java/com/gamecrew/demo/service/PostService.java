package com.gamecrew.demo.service;

import com.gamecrew.demo.domain.Post;
import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    final PostRepository postRepository;

    public void savePost(User user, Post post) {
        postRepository.save(user,post);
    }

    public List<Post> getPostsWithBrawlers(int page, int size) {
        return postRepository.findPostsWithBrawlers(page,size);
    }
}
