package com.hotel.Paradise_Hotel.controller;

import com.hotel.Paradise_Hotel.exception.UserAlreadyExistsException;
import com.hotel.Paradise_Hotel.model.User;
import com.hotel.Paradise_Hotel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final UserService userService;


@PostMapping("/register-user")
    public ResponseEntity<?> registerUser(User user){

        try {
            userService.registerUser(user);
            return ResponseEntity.ok("Registration Successful");
        }catch (UserAlreadyExistsException e){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        }
    }
}
