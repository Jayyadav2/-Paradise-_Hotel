package com.hotel.Paradise_Hotel.service;

import com.hotel.Paradise_Hotel.model.BookedRoom;

import java.util.List;

public interface BookingService {
    List<BookedRoom> getAllBookingsByRoomID(Long roomId);

    List<BookedRoom> getAllBookings();

    BookedRoom findByBookingConfirmationCode(String confimationCode);

    String saveBooking(Long roomId, BookedRoom bookingRequest);

    void cancelBooking(Long bookingId);
}
