package com.yangche.gatewayservice.model.to;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

@Data
public class SubscribeTO implements Serializable {

    @NotNull
    private Long userId;
}
