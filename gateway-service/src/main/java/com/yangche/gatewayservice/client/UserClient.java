package com.yangche.gatewayservice.client;

import com.yangche.gatewayservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping(value = "/user")
   User getUserById(@RequestParam("userId") Long id);

}
