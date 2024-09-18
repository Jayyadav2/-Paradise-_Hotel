package com.hotel.Paradise_Hotel.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException( String massage) {
        super(massage);
    }
}
