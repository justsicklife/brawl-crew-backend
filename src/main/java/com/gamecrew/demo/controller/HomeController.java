package com.gamecrew.demo.controller;

import com.gamecrew.demo.domain.Post;
import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.dto.api.PostDTO;

import com.gamecrew.demo.dto.request.LoginInfoDto;
import com.gamecrew.demo.dto.request.PostInfoDto;
import com.gamecrew.demo.service.PostService;
import com.gamecrew.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "HomeController", description = "홈 컨트롤러")
public class HomeController {

    final UserService userService;
    final PostService postService;

    final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/posts")
    @Operation(summary = "작성글",description = "작성글을 10개 단위만큼 가져온다")
    public List<PostDTO> getPosts() {
        log.info("getPosts");
        return postService.getPostsWithUsers(0,10);
    }

    @PostMapping("/post")
    @Operation(summary = "게시글 작성",description = "게시글을 작성한다")
    public String createPost(@RequestBody PostInfoDto postInfoDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User user = userService.userFindById(postInfoDto.getId());

        Post post = new Post();
        post.setMemo(postInfoDto.getMemo());

        post.setUser(user);

        postService.savePost(post);

        return "success";
    }

    @PostMapping("/login")
    @Operation(summary = "로그인",description = "로그인을 하면 웹토큰을 반환한다")
    public ResponseEntity<String> login(@RequestBody LoginInfoDto loginInfoDto) {
        log.info(loginInfoDto.toString());

        String token = userService.verify(loginInfoDto);

        if("fail".equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    @Operation(summary = "회원가입",description = "화원가입을 한다")
    public String register(@RequestBody User user) {
        log.info(user.toString());
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        userService.SaveUser(user);
        return "success";
    }
}