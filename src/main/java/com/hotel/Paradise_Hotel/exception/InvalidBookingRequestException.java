package com.hotel.Paradise_Hotel.exception;

public class InvalidBookingRequestException  extends  RuntimeException{
    public InvalidBookingRequestException(String massage){
        super(massage);
    }
}
