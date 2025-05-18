package com.gamecrew.demo.service;

import com.gamecrew.demo.domain.Post;
import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.dto.api.PostDTO;
import com.gamecrew.demo.mapper.PostMapper;
import com.gamecrew.demo.repository.PostRepository;
import com.gamecrew.demo.repository.UserRepo;
import com.gamecrew.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final UserRepo userRepo;

    public void savePost(Long userId,String memo) {
        User user = userRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을수 없습니다."));

        log.info(user.toString());

        Post post = new Post();
        post.setMemo(memo);
        post.setUser(user);

        postRepository.save(post);
    }

    public List<PostDTO> getPostsWithUsers(int page, int size) {
        return postMapper.findPostsWithUsers(page, size);
    }

}
