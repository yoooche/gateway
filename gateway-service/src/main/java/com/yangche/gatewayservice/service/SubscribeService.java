package com.yangche.gatewayservice.service;

import com.yangche.gatewayservice.model.to.SubscribeTO;

public interface SubscribeService {

    String subscribe(SubscribeTO subscribeTO);

    String unsubscribe(SubscribeTO subscribeTO);

}
