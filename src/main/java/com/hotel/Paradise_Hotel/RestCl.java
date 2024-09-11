package com.hotel.Paradise_Hotel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestCl {

    @GetMapping("/hello")
    public String myCode(){
        return "hello";
    }
}
