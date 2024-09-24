package com.hotel.Paradise_Hotel.exception;

public class RoleAlreadyExistException extends RuntimeException {
    public RoleAlreadyExistException(String massage) {
        super(massage);
    }
}
