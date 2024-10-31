package com.gamecrew.demo.service;

import com.gamecrew.demo.domain.Brawler;
import com.gamecrew.demo.domain.Post;
import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.domain.UserBrawler;
import com.gamecrew.demo.dto.service.BrawlerDto;
import com.gamecrew.demo.dto.service.PostWithBrawlerDto;
import com.gamecrew.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    final PostRepository postRepository;

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public List<PostWithBrawlerDto> getPostsWithBrawlers(int page, int size) {

        List<PostWithBrawlerDto> postWithBrawlerDtoList = new ArrayList<>();

        // Post 객체에서 User를 통해 UserBrawler를 지연 로딩으로 가져오는 예
        List<Post> posts = postRepository.findPostsWithUsers(page, size);
        for (Post post : posts) {
            User user = post.getUser();  // User는 fetch join으로 로드됨

            PostWithBrawlerDto postWithBrawlerDto = PostWithBrawlerDto.builder()
                    .postId(post.getPostId())
                    .userId(user.getUserId())
                    .memo(post.getMemo())
                    .userTrophyCount(user.getTrophies())
                    .username(user.getName())
                    .build();
            List<UserBrawler> userBrawlers = user.getUserBrawlers();  // UserBrawler는 지연 로딩으로 로드됨
            List<BrawlerDto> brawlerDtoList = new ArrayList<>();
            for (UserBrawler userBrawler : userBrawlers) {
                Brawler brawler = userBrawler.getBrawler();  // Brawler도 지연 로딩
                // 필요한 작업 수행

                BrawlerDto brawlerDto = BrawlerDto.builder()
                        .brawlerId(brawler.getBrawlerId())
                        .brawlerName(brawler.getName())
                        .trophyCount(userBrawler.getTrophy())
                        .build();

                brawlerDtoList.add(brawlerDto);
            }

            postWithBrawlerDto.setBrawlers(brawlerDtoList);

            postWithBrawlerDtoList.add(postWithBrawlerDto);
        }

        System.out.println("start");
        for (PostWithBrawlerDto postWithBrawlerDto : postWithBrawlerDtoList) {
            System.out.println("postWithBrawlerDto = " + postWithBrawlerDto);
        }
        System.out.println("end");

        return postWithBrawlerDtoList;
    }
}
