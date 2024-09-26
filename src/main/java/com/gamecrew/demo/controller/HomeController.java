package com.gamecrew.demo.controller;

import com.gamecrew.demo.domain.User;
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

    @GetMapping("/users")
    @ResponseBody
    public List<User> getUser() {
        List<User> usersWithBrawlers = userService.getUsersWithBrawlers(0, 10);
        System.out.println("usersWithBrawlers = " + usersWithBrawlers);
        return usersWithBrawlers;
    }

    @PostMapping("/user")
    @ResponseBody
    public String createUser(@RequestBody User user) {
        log.info("createUser");
        userService.saveUserAndTopBrawlers(user);
        return "success";
    }
}
