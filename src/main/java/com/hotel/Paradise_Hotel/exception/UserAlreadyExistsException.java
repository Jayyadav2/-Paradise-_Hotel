package com.hotel.Paradise_Hotel.exception;

public class UserAlreadyExistsException extends RuntimeException {


    public UserAlreadyExistsException(String massage) {
        super(massage);

    }
}
