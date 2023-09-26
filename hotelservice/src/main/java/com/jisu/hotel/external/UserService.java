package com.jisu.hotel.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "USER-SERVICE")
public interface UserService {

    @GetMapping("/users/fullname/{userId}")
    String getUser(@PathVariable String userId);
}
