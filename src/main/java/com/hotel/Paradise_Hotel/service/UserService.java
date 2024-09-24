package com.hotel.Paradise_Hotel.service;

import com.hotel.Paradise_Hotel.exception.UserAlreadyExistsException;
import com.hotel.Paradise_Hotel.model.User;

import java.util.List;

public interface UserService {

    User registerUser(User user) throws UserAlreadyExistsException;
    List<User> getUsers();

    void  deleteUser(String email);

    User getUser(String email);
}
