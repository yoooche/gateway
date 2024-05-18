package com.yangche.gatewayservice.service;

import com.yangche.gatewayservice.model.Role;
import com.yangche.gatewayservice.model.User;
import com.yangche.gatewayservice.model.to.RegisterTO;
import java.util.List;

public interface UserService {

    User getUserInfo(Long id);

    void register(RegisterTO registerTO);

    User getUserByUsername(String username);

    List<Role> getRoleByUserId(Long id);

}
