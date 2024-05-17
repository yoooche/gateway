package com.yangche.gatewayservice.service;

import com.yangche.gatewayservice.model.User;
import com.yangche.gatewayservice.model.to.RegisterTO;

public interface UserService {

    User getUserInfo(Long id);

    void register(RegisterTO registerTO);

    User getUserByUsername(String username);

}
