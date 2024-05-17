package com.yangche.gatewayservice.service.impl;

import com.yangche.gatewayservice.model.User;
import com.yangche.gatewayservice.model.to.RegisterTO;
import com.yangche.gatewayservice.repository.IUserRepo;
import com.yangche.gatewayservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final IUserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserInfo(Long id) {
        return userRepo.findByUserId(id);
    }

    @Override
    public void register(RegisterTO to) {
        User user = new User();
        var password = passwordEncoder.encode(to.getPassword());
        user.setUsername(to.getUsername());
        user.setPassword(password);
        user.setEmail(to.getEmail());
        user.setPhone(to.getPhone());
        user.setAddress(to.getAddress());
        userRepo.saveAndFlush(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUserName(username);
    }
}
