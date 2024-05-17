package com.yangche.gatewayservice.controller;

import com.yangche.gatewayservice.model.User;
import com.yangche.gatewayservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUserInfo(@RequestParam("userId") Long userId) {
        return userService.getUserInfo(userId);
    }

}
