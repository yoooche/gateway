package com.yangche.gatewayservice.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/event")
public class EventController {

    @PostMapping("/list")
    public String getEventList() {
        return "gain event list";
    }

    @PostMapping("/favorite")
    public String addToFavorList() {
        return "add event to favorite list";
    }

}
