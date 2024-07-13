package com.yangche.gatewayservice.controller;

import com.yangche.gatewayservice.model.User;
import com.yangche.gatewayservice.model.to.RegisterTO;
import com.yangche.gatewayservice.service.UserService;
import com.yangche.gatewayservice.to.UserLoginTO;
import com.yangche.gatewayservice.utils.JwtUtil;
import jakarta.validation.Valid;
import java.util.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserService userService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public User getUserInfo(@RequestParam("userId") Long userId) {
        return userService.getUserInfo(userId);
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterTO registerTO) {
        userService.register(registerTO);
        return "註冊成功";
    }

//    @PostMapping("/login")
//    public String login(Authentication authentication) {
//        var username = authentication.getName();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        return "登入成功！帳號 " + username + " 的權限為: " + authorities;
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginTO userLoginTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginTO.getUsername(), userLoginTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }

}
