package com.yangche.gatewayservice.service.impl;

import com.yangche.gatewayservice.model.User;
import com.yangche.gatewayservice.repository.IUserRepo;
import com.yangche.gatewayservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final IUserRepo userRepo;

    public UserServiceImpl(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User getUserInfo(Long id) {
        return userRepo.findByUserId(id);
    }
}
