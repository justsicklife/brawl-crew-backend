package com.gamecrew.demo.controller;

import com.gamecrew.demo.domain.Post;
import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.dto.request.PostInfoDto;
import com.gamecrew.demo.dto.service.PostWithBrawlerDto;
import com.gamecrew.demo.service.PostService;
import com.gamecrew.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    final UserService userService;
    final PostService postService;


    @RequestMapping("/")
    public String home(Model model) {
        log.info("home controller");
        List<User> usersWithBrawlers = userService.getUsersWithBrawlers(0, 10);

        model.addAttribute("users",usersWithBrawlers);
        
        return "home";
    }



    @PostMapping("/new")
    public String newUser(User user) {
        log.info(user.toString());
        userService.saveUserAndTopBrawlers(user);

        return "redirect:/";
    }

    @GetMapping("/posts")
    @ResponseBody
    public List<PostWithBrawlerDto> getUser() {
        return postService.getPostsWithBrawlers(0, 10);
    }

    @PostMapping("/user")
    @ResponseBody
    public String createUser(@RequestBody User user) {
        log.info("createUser");
        userService.saveUserAndTopBrawlers(user);
        return "success";
    }

    @PostMapping("/post")
    @ResponseBody
    public String createPost(@RequestBody PostInfoDto postInfoDto) {

        User user = userService.userFindById(postInfoDto.getId());

        Post post = new Post();
        post.setMemo(postInfoDto.getMemo());

        post.setUser(user);

        postService.savePost(post);

        return "success";
    }
}
