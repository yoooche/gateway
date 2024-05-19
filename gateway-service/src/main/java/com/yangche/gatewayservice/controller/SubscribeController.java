package com.yangche.gatewayservice.controller;

import com.yangche.gatewayservice.model.to.SubscribeTO;
import com.yangche.gatewayservice.service.SubscribeService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    private final SubscribeService subscribeService;

    public SubscribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @PostMapping
    public String subscribe(@RequestBody @Valid SubscribeTO subscribeTO) {
        return subscribeService.subscribe(subscribeTO);
    }

    @PostMapping("/remove")
    public String unsubscribe(@RequestBody @Valid SubscribeTO subscribeTO) {
        return subscribeService.unsubscribe(subscribeTO);
    }

}
