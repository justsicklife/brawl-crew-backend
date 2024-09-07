package com.gamecrew.demo.controller;

import com.gamecrew.demo.domain.User;
import com.gamecrew.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    final UserService userService;

    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home";
    }

    @PostMapping("/new")
    public String newUser(User user) {
        log.info(user.toString());
        userService.save(user);

        return "redirect:/";
    }

}
